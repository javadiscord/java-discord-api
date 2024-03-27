package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumedEventListener implements EventListener {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onEvent(
            GatewayEvent gatewayEvent, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.trace("Resume event consumed");
    }
}
