package com.javadiscord.jdi.internal.gateway;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.identify.IdentifyRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebSocketFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebSocketManager {
    private static final Logger LOGGER = LogManager.getLogger(WebSocketManager.class);
    private final GatewaySetting gatewaySetting;
    private final IdentifyRequest identifyRequest;
    private final Vertx vertx;
    private final WebSocketRetryHandler retryHandler;
    private final Cache cache;
    private WebSocket webSocket;
    private WebSocketClient webSocketClient;
    private boolean retryAllowed;

    public WebSocketManager(
        GatewaySetting gatewaySetting, IdentifyRequest identifyRequest, Cache cache
    ) {
        this.gatewaySetting = gatewaySetting;
        this.identifyRequest = identifyRequest;
        this.vertx = Vertx.vertx();
        this.retryHandler = new WebSocketRetryHandler(vertx);
        this.cache = cache;
    }

    public void start(ConnectionMediator connectionMediator) {
        String gatewayURL = connectionMediator.getConnectionDetails().getGatewayURL();

        WebSocketConnectOptions webSocketConnectOptions =
            new WebSocketConnectOptions()
                .addHeader("Origin", "localhost")
                .setAbsoluteURI(
                    "%s/?v=%d&encoding=%s"
                        .formatted(
                            gatewayURL,
                            gatewaySetting.getApiVersion(),
                            gatewaySetting.getEncoding()
                        )
                )
                .setSsl(true);

        webSocketClient = vertx.createWebSocketClient();
        retryAllowed = true;
        webSocketClient.connect(webSocketConnectOptions)
            .onSuccess(
                webSocket -> {
                    LOGGER.info("Connected to Discord");

                    this.webSocket = webSocket;

                    WebSocketHandler webSocketHandler =
                        new WebSocketHandler(connectionMediator, retryHandler, cache);

                    webSocketHandler.handle(webSocket);

                    webSocket.frameHandler(frame -> frameHandler(frame, webSocketHandler));

                    if (retryHandler.hasRetried()) {
                        retryHandler.clear();
                        sendResumeEvent(webSocket, connectionMediator);
                    } else {
                        sendIdentify(webSocket, identifyRequest);
                    }
                }
            )
            .onFailure(
                error -> {
                    LOGGER.warn("Failed to connect to {} {}", gatewayURL, error.getCause());
                    if (retryAllowed) {
                        retryHandler.retry(() -> restart(connectionMediator));
                    }
                }
            );
    }

    private void frameHandler(WebSocketFrame frame, WebSocketHandler webSocketHandler) {
        if (frame.isClose()) {
            webSocketHandler.handleClose(frame.closeStatusCode(), frame.closeReason());
        }
    }

    public void restart(ConnectionMediator connectionMediator) {
        retryAllowed = true;
        stop();
        start(connectionMediator);
    }

    public void stop() {
        if (webSocket != null && !webSocket.isClosed()) {
            webSocket.close();
        }
        webSocketClient.close()
            .onSuccess(res -> LOGGER.info("Web socket client has been shutdown"))
            .onFailure(err -> LOGGER.error("Failed to shutdown web socket client", err));
        retryAllowed = false;
        vertx.close()
            .onSuccess(res -> LOGGER.info("Gateway has shutdown"))
            .onFailure(err -> LOGGER.error("Failed to shutdown gateway", err));
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    private static void sendIdentify(WebSocket webSocket, IdentifyRequest identifyRequest) {
        try {
            webSocket.write(
                Buffer.buffer((new ObjectMapper().writeValueAsString(identifyRequest)))
            );
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to send identify request, restarting bot");
        }
    }

    private void sendResumeEvent(WebSocket webSocket, ConnectionMediator connectionMediator) {
        String botToken = identifyRequest.getD().getToken();
        String sessionId = connectionMediator.getConnectionDetails().getSessionId();
        int sequence = connectionMediator.getConnectionDetails().getSequence();
        int opcode = GatewayOpcode.RESUME;
        webSocket.write(
            Buffer.buffer(
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
            )
        );
    }
}
