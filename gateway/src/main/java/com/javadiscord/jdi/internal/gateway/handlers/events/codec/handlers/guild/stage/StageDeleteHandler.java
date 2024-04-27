package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.stage.Stage;

public class StageDeleteHandler implements EventHandler<Stage> {
    @Override
    public void handle(
        Stage event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(event.guildId());
        if (cacheInterface.isCached(event.id(), event.getClass())) {
            cacheInterface.remove(event.id(), event.getClass());
        }
    }
}
