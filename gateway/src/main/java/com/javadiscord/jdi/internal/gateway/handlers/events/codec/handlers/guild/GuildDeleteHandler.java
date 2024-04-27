package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Guild;

public class GuildDeleteHandler implements EventHandler<Guild> {
    @Override
    public void handle(
        Guild event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        cache.removeGuild(event.id());
    }
}
