package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Entitlement;

public class EntitlementDeleteHandler implements EventHandler<Entitlement> {
    @Override
    public void handle(Entitlement event, ConnectionMediator connectionMediator, Discord discord) {
        CacheInterface<?> cache = discord.getCache().getCacheForGuild(event.guildId());
        if (cache.isCached(event.id(), event.getClass())) {
            cache.remove(event.id(), event.getClass());
        }
    }
}
