package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.channel.Channel;

public class ChannelCreateHandler implements EventHandler<Channel> {

    @Override
    public void handle(
        Channel event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
