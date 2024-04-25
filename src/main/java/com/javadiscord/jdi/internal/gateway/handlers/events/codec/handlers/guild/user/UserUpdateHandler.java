package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.user.User;

public class UserUpdateHandler implements EventHandler<User> {

    @SuppressWarnings("unchecked")
    @Override
    public void handle(User event, ConnectionMediator connectionMediator, Discord discord) {
        discord.getCache().getCachedGuilds().forEach((guildId, cache) -> {
            if (cache.isCached(event.id(), User.class)) {
                cache.update(event.id(), event);
            }
        });
    }
}
