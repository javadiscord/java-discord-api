package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.channel.Channel;

public class ChannelDeleteHandler implements EventHandler<Channel> {
    @Override
    public void handle(Channel event, ConnectionMediator connectionMediator, Discord discord) {
        CacheInterface<?> cache = discord.getCache().getCacheForGuild(event.guildId());
        if (cache.isCached(event.id(), event.getClass())) {
            cache.remove(event.id(), event.getClass());
        }
    }
}
