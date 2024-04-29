package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user;

import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class UserUpdateHandler implements EventHandler<User> {

    @SuppressWarnings("unchecked")
    @Override
    public void handle(User event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCachedGuilds()
                .forEach(
                        (guildId, cacheInterface) -> {
                            if (cacheInterface.isCached(event.id(), User.class)) {
                                cacheInterface.update(event.id(), event);
                            }
                        });
    }
}
