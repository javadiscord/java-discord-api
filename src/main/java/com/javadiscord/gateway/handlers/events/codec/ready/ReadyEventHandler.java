package com.javadiscord.gateway.handlers.events.codec.ready;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadyEventHandler implements EventHandler<ReadyEvent> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(ReadyEvent event, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.trace("Ready event consumed");
    }
}
