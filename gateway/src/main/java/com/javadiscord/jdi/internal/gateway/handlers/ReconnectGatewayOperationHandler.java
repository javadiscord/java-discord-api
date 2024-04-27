package com.javadiscord.jdi.internal.gateway.handlers;

import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;

public class ReconnectGatewayOperationHandler implements GatewayOperationHandler {
    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator) {
        connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
    }
}
