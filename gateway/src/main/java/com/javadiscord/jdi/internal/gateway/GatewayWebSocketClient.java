package com.javadiscord.jdi.internal.gateway;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.function.Consumer;

public class GatewayWebSocketClient extends WebSocketClient {
    private final EmptyConsumer onSuccess;
    private final Consumer<Exception> onFailure;
    private Consumer<String> onReceive = (message) -> {};
    private EmptyConsumer onClose = () -> {};
    private Consumer<Framedata> frameHandler = (framedata) -> {};

    public GatewayWebSocketClient(URI serverUri, EmptyConsumer onSuccess, Consumer<Exception> onFailure) {
        super(serverUri);
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        onSuccess.accept();
    }

    @Override
    public void onMessage(String s) {
        onReceive.accept(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        onClose.accept();
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

    public void setOnClose(EmptyConsumer onClose) {
        this.onClose = onClose;
    }

    public void setFrameHandler(Consumer<Framedata> frameHandler) {
        this.frameHandler = frameHandler;
    }

    @FunctionalInterface
    public interface EmptyConsumer {
        void accept();
    }
}
