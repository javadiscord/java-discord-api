package com.javadiscord.jdi.internal.gateway.handlers;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;

public class ReconnectGatewayOperationHandler implements GatewayOperationHandler {
    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord) {
        connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
    }
}
