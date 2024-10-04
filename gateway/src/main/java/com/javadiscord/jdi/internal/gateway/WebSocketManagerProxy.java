package com.javadiscord.jdi.internal.gateway;

import org.java_websocket.client.WebSocketClient;

public class WebSocketManagerProxy {
    private final WebSocketManager webSocketManager;

    public WebSocketManagerProxy(WebSocketManager webSocketManager) {
        this.webSocketManager = webSocketManager;
    }

    public void start(ConnectionMediator connectionMediator) {
        webSocketManager.start(connectionMediator);
    }

    public void restart(ConnectionMediator connectionMediator) {
        webSocketManager.restart(connectionMediator);
    }

    public WebSocketClient getWebSocket() {
        return webSocketManager.getWebSocket();
    }
}
