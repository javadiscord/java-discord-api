package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.stage.Stage;

public class StageCreateHandler implements EventHandler<Stage> {
    @Override
    public void handle(Stage event, ConnectionMediator connectionMediator, Discord discord) {
        discord.getCache().getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
