package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.scheduled_event.EventUser;

public class ScheduledEventUserAddHandler implements EventHandler<EventUser> {
    @Override
    public void handle(
        EventUser event, ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
