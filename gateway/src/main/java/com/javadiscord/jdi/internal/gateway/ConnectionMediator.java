package com.javadiscord.jdi.internal.gateway;

import com.javadiscord.jdi.internal.gateway.handlers.events.codec.GatewayObserver;

import java.util.ArrayList;
import java.util.List;

public class ConnectionMediator {
    private final ConnectionDetails connectionDetails;
    private final WebSocketManagerProxy webSocketManagerProxy;
    private final List<GatewayObserver> observers = new ArrayList<>();

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

    public void addObserver(GatewayObserver observer) {
        observers.add(observer);
    }

    public List<GatewayObserver> getObservers() {
        return observers;
    }
}
