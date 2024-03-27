package com.javadiscord.gateway.handlers.events.codec.resume;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeEventHandler implements EventHandler<ResumeEvent> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(ResumeEvent message, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.trace("Resume message consumed");
    }
}
