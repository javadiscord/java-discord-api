package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.MessageHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReconnectMessageHandler implements MessageHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.trace("Received reconnect request");
        connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
    }
}
