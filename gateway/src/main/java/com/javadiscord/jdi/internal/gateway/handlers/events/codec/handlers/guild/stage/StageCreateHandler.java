package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage;

import com.javadiscord.jdi.core.models.stage.Stage;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class StageCreateHandler implements EventHandler<Stage> {
    @Override
    public void handle(Stage event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).add(event.id(), event);
    }
}
