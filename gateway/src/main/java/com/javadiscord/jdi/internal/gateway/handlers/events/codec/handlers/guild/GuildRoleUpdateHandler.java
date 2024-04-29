package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.models.guild.Role;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class GuildRoleUpdateHandler implements EventHandler<Role> {
    @Override
    public void handle(Role event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).update(event.id(), event);
    }
}
