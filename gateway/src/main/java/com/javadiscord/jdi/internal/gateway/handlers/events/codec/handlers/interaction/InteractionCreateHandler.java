package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.interaction;

import com.javadiscord.jdi.core.models.guild.Interaction;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class InteractionCreateHandler implements EventHandler<Interaction> {
    @Override
    public void handle(Interaction event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
