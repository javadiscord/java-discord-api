package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.scheduled_event.ScheduledEvent;

public class ScheduledEventDeleteHandler implements EventHandler<ScheduledEvent> {
    @Override
    public void handle(
        ScheduledEvent event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(event.guildId());
        if (cacheInterface.isCached(event.id(), event.getClass())) {
            cacheInterface.remove(event.id(), event.getClass());
        }
    }
}
