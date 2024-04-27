package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class GuildUpdateEventHandler implements EventHandler<Guild> {
    @Override
    public void handle(Guild event, ConnectionMediator connectionMediator, Cache cache) {
        if (!cache.isGuildCached(event.id())) {
            cache.createCache(event.id());
        }
        cache.getCacheForGuild(event.id()).update(event.id(), event);
    }
}
