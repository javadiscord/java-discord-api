package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Entitlement;

public class EntitlementUpdateHandler implements EventHandler<Entitlement> {
    @Override
    public void handle(
        Entitlement event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
