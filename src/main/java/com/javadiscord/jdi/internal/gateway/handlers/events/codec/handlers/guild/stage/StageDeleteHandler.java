package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.stage.Stage;

public class StageDeleteHandler implements EventHandler<Stage> {
    @Override
    public void handle(Stage event, ConnectionMediator connectionMediator, Discord discord) {
        CacheInterface<?> cache = discord.getCache().getCacheForGuild(event.guildId());
        if (cache.isCached(event.id(), event.getClass())) {
            cache.remove(event.id(), event.getClass());
        }
    }
}
