package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Guild;

public class GuildCreateHandler implements EventHandler<Guild> {

    @Override
    public void handle(Guild event, ConnectionMediator connectionMediator, Discord discord) {
        discord.getCache().cacheGuild(event);
    }
}
