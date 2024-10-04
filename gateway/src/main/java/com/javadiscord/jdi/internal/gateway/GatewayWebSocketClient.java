package com.javadiscord.jdi.internal.gateway;

import java.net.URI;
import java.util.function.Consumer;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class GatewayWebSocketClient extends WebSocketClient {
    private final Runnable onSuccess;
    private final Consumer<Exception> onFailure;
    private Consumer<String> onReceive = (message) -> {};
    private Runnable onClose = () -> {};
    private Consumer<Framedata> frameHandler = (framedata) -> {};

    public GatewayWebSocketClient(
        URI serverUri, Runnable onSuccess, Consumer<Exception> onFailure
    ) {
        super(serverUri);
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        onSuccess.run();
    }

    @Override
    public void onMessage(String s) {
        onReceive.accept(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        onClose.run();
    }

    @Override
    public void onError(Exception e) {
        onFailure.accept(e);
    }

    @Override
    public void onWebsocketPing(WebSocket conn, Framedata f) {
        frameHandler.accept(f);
    }

    @Override
    public void onWebsocketPong(WebSocket conn, Framedata f) {
        frameHandler.accept(f);
    }

    public void setOnReceive(Consumer<String> onReceive) {
        this.onReceive = onReceive;
    }

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }

    public void setFrameHandler(Consumer<Framedata> frameHandler) {
        this.frameHandler = frameHandler;
    }
}
