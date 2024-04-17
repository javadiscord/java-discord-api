package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.ScheduledEvent;

public class ScheduledEventUpdateHandler implements EventHandler<ScheduledEvent> {
    @Override
    public void handle(ScheduledEvent event, ConnectionMediator connectionMediator,
            Discord discord) {
    }
}
