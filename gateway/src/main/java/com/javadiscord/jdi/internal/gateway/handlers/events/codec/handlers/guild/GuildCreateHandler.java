package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.models.guild.GuildModel;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class GuildCreateHandler implements EventHandler<GuildModel> {

    @Override
    public void handle(GuildModel event, ConnectionMediator connectionMediator, Cache cache) {
        cache.createCache(event.id());
        cache.getCacheForGuild(event.id()).add(event.id(), event);
    }
}
