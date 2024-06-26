package com.javadiscord.jdi.internal.gateway;

import java.util.HashMap;
import java.util.Map;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.handlers.GatewayOperationHandler;
import com.javadiscord.jdi.internal.gateway.handlers.ReconnectGatewayOperationHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.EventCodecHandler;
import com.javadiscord.jdi.internal.gateway.handlers.heartbeat.HeartbeatAckOperationHandler;
import com.javadiscord.jdi.internal.gateway.handlers.heartbeat.HeartbeatService;
import com.javadiscord.jdi.internal.gateway.handlers.heartbeat.HelloOperationHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebSocketHandler implements Handler<WebSocket> {
    private static final Logger LOGGER = LogManager.getLogger(WebSocketHandler.class);
    private static final ObjectMapper OBJECT_MAPPER =
        JsonMapper.builder().addModule(new JavaTimeModule()).build();
    private static final Map<Integer, GatewayOperationHandler> OPERATION_HANDLER = new HashMap<>();
    private final ConnectionMediator connectionMediator;
    private final Cache cache;
    private final HeartbeatService heartbeatService;

    public WebSocketHandler(
        ConnectionMediator connectionMediator,
        Cache cache, HeartbeatService heartbeatService
    ) {
        this.connectionMediator = connectionMediator;
        this.cache = cache;
        this.heartbeatService = heartbeatService;
        registerHandlers();
    }

    private void registerHandlers() {
        OPERATION_HANDLER.put(GatewayOpcode.HELLO, new HelloOperationHandler(heartbeatService));
        OPERATION_HANDLER.put(
            GatewayOpcode.HEARTBEAT_ACK, new HeartbeatAckOperationHandler(heartbeatService)
        );
        OPERATION_HANDLER.put(
            GatewayOpcode.HEARTBEAT, new HeartbeatAckOperationHandler(heartbeatService)
        );
        OPERATION_HANDLER.put(GatewayOpcode.DISPATCH, new EventCodecHandler(cache));

        ReconnectGatewayOperationHandler reconnectMessageHandler =
            new ReconnectGatewayOperationHandler();
        OPERATION_HANDLER.put(GatewayOpcode.RECONNECT, reconnectMessageHandler);
        OPERATION_HANDLER.put(GatewayOpcode.INVALID_SESSION, reconnectMessageHandler);
    }

    @Override
    public void handle(WebSocket webSocket) {
        webSocket.handler(this::handleMessage);
        webSocket.closeHandler(this::handleClose);
    }

    private void handleMessage(Buffer buffer) {
        LOGGER.trace("Received message from gateway: {}", buffer);

        try {
            GatewayEvent gatewayEvent =
                OBJECT_MAPPER.readValue(buffer.toString(), GatewayEvent.class);

            connectionMediator.getConnectionDetails().setSequence(gatewayEvent.sequenceNumber());

            if (OPERATION_HANDLER.containsKey(gatewayEvent.opcode())) {
                GatewayOperationHandler gatewayOperationHandler =
                    OPERATION_HANDLER.get(gatewayEvent.opcode());
                gatewayOperationHandler.handle(gatewayEvent, connectionMediator);
            } else {
                LOGGER.warn("Unknown opcode {}", gatewayEvent.opcode());
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse Discord gateway event", e);
        }
    }

    private void handleClose(Void unused) {
        LOGGER.warn(
            "The web socket connection to discord was closed. You will no longer receive"
                + " gateway events."
        );
    }

    void handleClose(int status, String reason) {
        switch (status) {
            case GatewayCloseEventCode.NOT_AUTHENTICATED:
            case GatewayCloseEventCode.AUTHENTICATION_FAILED:
                LOGGER.error("Invalid bot token provided");
                break;
            case GatewayCloseEventCode.RATE_LIMITED:
                LOGGER.warn("Rate limit has been hit, reconnecting");
                connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
                break;
            case GatewayCloseEventCode.SESSION_TIMED_OUT:
                LOGGER.warn("Session has timed out restarting");
                connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
                break;
            case GatewayCloseEventCode.INVALID_API_VERSION:
            case GatewayCloseEventCode.UNKNOWN_OPCODE:
                LOGGER.error(
                    "Discord has updated their API, please use the latest updates on"
                        + " https://javadiscord.com/"
                );
                break;
            case GatewayCloseEventCode.DISALLOWED_INTENTS:
                LOGGER.error(
                    "You do not have all the required intents set on your bot. Visit"
                        + " https://discord.com/developers/applications/"
                );
                break;
            default:
                LOGGER.error("Web socket close reason [{}] {}", status, reason);
                break;
        }
    }
}
