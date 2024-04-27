package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement;

import com.javadiscord.jdi.core.models.guild.Entitlement;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class EntitlementCreateHandler implements EventHandler<Entitlement> {
    @Override
    public void handle(Entitlement event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
