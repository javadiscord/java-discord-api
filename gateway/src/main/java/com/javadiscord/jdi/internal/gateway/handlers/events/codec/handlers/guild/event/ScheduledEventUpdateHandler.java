package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.scheduled_event.ScheduledEvent;

public class ScheduledEventUpdateHandler implements EventHandler<ScheduledEvent> {
    @Override
    public void handle(
        ScheduledEvent event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        cache.getCacheForGuild(event.guildId()).update(event.id(), event);
    }
}
