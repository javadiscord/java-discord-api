package com.javadiscord.jdi.internal.gateway.handlers;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;

public interface GatewayOperationHandler {
    void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord);
}
