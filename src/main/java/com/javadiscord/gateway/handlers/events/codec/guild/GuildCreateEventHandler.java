package com.javadiscord.gateway.handlers.events.codec.guild;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.gateway.handlers.events.codec.ready.Guild;

public class GuildCreateEventHandler implements EventHandler<Guild> {

    @Override
    public void handle(Guild event, ConnectionMediator connectionMediator, Discord discord) {}
}
