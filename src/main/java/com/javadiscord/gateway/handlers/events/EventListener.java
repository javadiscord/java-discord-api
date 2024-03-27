package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;

public interface EventListener {
    void onEvent(GatewayEvent gatewayEvent, ConnectionMediator connectionMediator, Discord discord);
}
