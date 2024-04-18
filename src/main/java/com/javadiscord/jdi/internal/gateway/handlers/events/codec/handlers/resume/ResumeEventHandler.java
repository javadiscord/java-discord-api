package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.resume;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeEventHandler implements EventHandler<ResumeEvent> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(
        ResumeEvent message, ConnectionMediator connectionMediator, Discord discord
    ) {
        LOGGER.trace("Resume message consumed");
    }
}
