package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Guild;

public class GuildCreateHandler implements EventHandler<Guild> {

    @Override
    public void handle(Guild event, ConnectionMediator connectionMediator, Cache cache) {
        cache.createCache(event.id());
        cache.getCacheForGuild(event.id()).add(event.id(), event);
    }
}
