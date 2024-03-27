package com.javadiscord.gateway;

public class ConnectionMediator {
    private final ConnectionDetails connectionDetails;
    private final WebSocketManagerProxy webSocketManagerProxy;

    public ConnectionMediator(
            ConnectionDetails connectionDetails, WebSocketManagerProxy webSocketManagerProxy) {
        this.connectionDetails = connectionDetails;
        this.webSocketManagerProxy = webSocketManagerProxy;
    }

    public ConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    public WebSocketManagerProxy getWebSocketManagerProxy() {
        return webSocketManagerProxy;
    }
}
