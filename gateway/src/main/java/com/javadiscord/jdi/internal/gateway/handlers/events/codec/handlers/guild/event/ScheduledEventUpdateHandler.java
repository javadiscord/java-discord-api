package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEvent;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class ScheduledEventUpdateHandler implements EventHandler<ScheduledEvent> {
    @Override
    public void handle(ScheduledEvent event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).update(event.id(), event);
    }
}
