package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.scheduledevent.EventUser;

public class ScheduledEventUserRemoveHandler implements EventHandler<EventUser> {
    @Override
    public void handle(EventUser event, ConnectionMediator connectionMediator, Discord discord) {}
}
