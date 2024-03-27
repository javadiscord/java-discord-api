package com.javadiscord.gateway.handlers.events.codec.guild;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

public class GuildCreateEventHandler implements EventHandler<GuildCreateEvent> {

    @Override
    public void handle(
            GuildCreateEvent event, ConnectionMediator connectionMediator, Discord discord) {}
}
