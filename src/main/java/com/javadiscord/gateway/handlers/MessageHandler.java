package com.javadiscord.gateway.handlers;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;

public interface MessageHandler {
    void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord);
}
