package com.javadiscord.jdi.internal.gateway;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.handlers.heartbeat.HeartbeatService;
import com.javadiscord.jdi.internal.gateway.identify.IdentifyRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.InternalApi;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.framing.PongFrame;

import java.net.URI;

public class WebSocketManager  {
    private static final Logger LOGGER = LogManager.getLogger(WebSocketManager.class);
    private final GatewaySetting gatewaySetting;
    private final IdentifyRequest identifyRequest;
    private final WebSocketRetryHandler retryHandler;
    private final Cache cache;
    private GatewayWebSocketClient client;
    private HeartbeatService heartbeatService;
    private boolean retryAllowed;

    public WebSocketManager(
        GatewaySetting gatewaySetting, IdentifyRequest identifyRequest, Cache cache
    ) {
        this.gatewaySetting = gatewaySetting;
        this.identifyRequest = identifyRequest;
        this.retryHandler = new WebSocketRetryHandler();
        this.cache = cache;
    }

    public void start(ConnectionMediator connectionMediator) {
        heartbeatService = new HeartbeatService(connectionMediator);
        retryAllowed = true;

        String gatewayURL = connectionMediator.getConnectionDetails().getGatewayURL();
        client = new GatewayWebSocketClient(
                URI.create("%s/?v=%d&encoding=%s"
                        .formatted(
                                gatewayURL,
                                gatewaySetting.getApiVersion(),
                                gatewaySetting.getEncoding()
                        )),
                () -> {
                    // Success
                    LOGGER.info("Connected to Discord");
                    WebSocketHandler webSocketHandler =
                            new WebSocketHandler(connectionMediator, cache, heartbeatService);

                    webSocketHandler.handle(client);

                    client.setFrameHandler(frame -> frameHandler(frame, webSocketHandler));

                    if (retryHandler.hasRetried()) {
                        retryHandler.clear();
                        sendResumeEvent(connectionMediator);
                    } else {
                        sendIdentify(client, identifyRequest);
                    }
                },
                (exception) -> {
                    // Error
                    LOGGER.warn("An error occurred in the gateway's connection: {} {}", gatewayURL, exception.getCause());
                    if (retryAllowed) {
                        retryHandler.retry(() -> restart(connectionMediator));
                    }
                }
        );
        client.connect();
    }

    private void frameHandler(Framedata frame, WebSocketHandler webSocketHandler) {
        switch (frame) {
            case CloseFrame closeFrame ->
                    webSocketHandler.handleClose(closeFrame.getCloseCode(), closeFrame.getMessage());
            case PingFrame pingFrame -> client.sendFrame(new PongFrame());
            case PongFrame pongFrame -> {}
            case null, default -> LOGGER.warn("Unhandled frame: {}", frame);
        }
    }

    public void restart(ConnectionMediator connectionMediator) {
        retryAllowed = true;
        stop();
        start(connectionMediator);
    }

    public void stop() {
        if (client != null && !client.isClosed()) {
            try {
                client.closeBlocking();
            } catch (InterruptedException e) {
                LOGGER.error("Failed to close websocket client: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        if (heartbeatService != null) {
            heartbeatService.stop();
        }

        retryAllowed = false;
    }

    public WebSocketClient getWebSocket() {
        return client;
    }

    private static void sendIdentify(org.java_websocket.client.WebSocketClient client, IdentifyRequest identifyRequest) {
        try {
            client.send(
                new ObjectMapper().writeValueAsString(identifyRequest)
            );
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to send identify request, restarting bot");
        }
    }

    private void sendResumeEvent(ConnectionMediator connectionMediator) {
        String botToken = identifyRequest.getD().getToken();
        String sessionId = connectionMediator.getConnectionDetails().getSessionId();
        int sequence = connectionMediator.getConnectionDetails().getSequence();
        int opcode = GatewayOpcode.RESUME;
        client.send(
            """
                {
                  "op": %d,
                  "d": {
                    "token": "%s",
                    "session_id": "%s",
                    "seq": %d
                  }
                }
                """
                .formatted(opcode, botToken, sessionId, sequence)
        );
    }
}
