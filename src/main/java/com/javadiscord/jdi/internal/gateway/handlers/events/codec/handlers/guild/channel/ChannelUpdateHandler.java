package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.channel.Channel;

public class ChannelUpdateHandler implements EventHandler<Channel> {
    @Override
    public void handle(Channel event, ConnectionMediator connectionMediator, Discord discord) {
        discord.getCache().getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
