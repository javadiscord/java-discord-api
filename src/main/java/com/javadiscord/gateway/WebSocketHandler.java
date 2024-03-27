package com.javadiscord.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.Discord;
import com.javadiscord.gateway.handlers.MessageHandler;
import com.javadiscord.gateway.handlers.events.EventHandler;
import com.javadiscord.gateway.handlers.events.ReconnectMessageHandler;
import com.javadiscord.gateway.handlers.heartbeat.HeartbeatAckHandler;
import com.javadiscord.gateway.handlers.heartbeat.HeartbeatService;
import com.javadiscord.gateway.handlers.heartbeat.HelloHandler;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class WebSocketHandler implements Handler<WebSocket> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Map<Integer, MessageHandler> MESSAGE_HANDLER = new HashMap<>();
    private final ConnectionMediator connectionMediator;
    private final WebSocketRetryHandler retryHandler;
    private final Discord discord;

    public WebSocketHandler(
            ConnectionMediator connectionMediator,
            WebSocketRetryHandler retryHandler,
            Discord discord) {
        this.connectionMediator = connectionMediator;
        this.retryHandler = retryHandler;
        this.discord = discord;
        registerHandlers();
    }

    private void registerHandlers() {
        HeartbeatService heartbeatService = new HeartbeatService(connectionMediator);
        MESSAGE_HANDLER.put(GatewayOpcode.HELLO, new HelloHandler(heartbeatService));
        MESSAGE_HANDLER.put(GatewayOpcode.HEARTBEAT_ACK, new HeartbeatAckHandler(heartbeatService));
        MESSAGE_HANDLER.put(GatewayOpcode.HEARTBEAT, new HeartbeatAckHandler(heartbeatService));
        MESSAGE_HANDLER.put(GatewayOpcode.DISPATCH, new EventHandler());

        ReconnectMessageHandler reconnectMessageHandler = new ReconnectMessageHandler();
        MESSAGE_HANDLER.put(GatewayOpcode.RECONNECT, reconnectMessageHandler);
        MESSAGE_HANDLER.put(GatewayOpcode.INVALID_SESSION, reconnectMessageHandler);
    }

    @Override
    public void handle(WebSocket webSocket) {
        webSocket.handler(this::handleMessage);
        webSocket.closeHandler(this::handleClose);
        webSocket.frameHandler(this::frameHandler);
    }

    private void handleMessage(Buffer buffer) {
        LOGGER.trace("Received message from gateway: {}", buffer);

        try {
            GatewayEvent gatewayEvent =
                    OBJECT_MAPPER.readValue(buffer.toString(), GatewayEvent.class);

            connectionMediator.getConnectionDetails().setSequence(gatewayEvent.sequenceNumber());

            if (MESSAGE_HANDLER.containsKey(gatewayEvent.opcode())) {
                MessageHandler messageHandler = MESSAGE_HANDLER.get(gatewayEvent.opcode());
                messageHandler.handle(gatewayEvent, connectionMediator, discord);
            } else {
                LOGGER.warn("Unknown opcode {}", gatewayEvent.opcode());
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse Discord gateway event", e);
        }
    }

    private void handleClose(Void unused) {
        retryHandler.retry(
                () -> connectionMediator.getWebSocketManagerProxy().start(connectionMediator));
    }

    private void frameHandler(WebSocketFrame frame) {
        if (frame.isClose()) {
            handleClose(frame.closeStatusCode(), frame.closeReason());
        }
    }

    private void handleClose(int status, String reason) {
        switch (status) {
            case GatewayCloseEventCode.NOT_AUTHENTICATED:
            case GatewayCloseEventCode.AUTHENTICATION_FAILED:
                LOGGER.error("Invalid bot token provided");
                break;
            case GatewayCloseEventCode.RATE_LIMITED:
                LOGGER.warn("Rate limit has been hit, reconnecting");

                break;
            case GatewayCloseEventCode.SESSION_TIMED_OUT:
                LOGGER.warn("Session has timed out restarting");
                connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
                break;
            case GatewayCloseEventCode.INVALID_API_VERSION:
            case GatewayCloseEventCode.UNKNOWN_OPCODE:
                LOGGER.error(
                        "Discord has updated their API, please use the latest updates on"
                                + " https://javadiscord.com/");
                break;
            default:
                LOGGER.error("[{}] {}", status, reason);
                break;
        }
    }
}
