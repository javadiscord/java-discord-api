package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel;

import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class ChannelUpdateHandler implements EventHandler<Channel> {
    @Override
    public void handle(Channel event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
