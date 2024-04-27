package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.Message;

public class MessageDeleteHandler implements EventHandler<Message> {
    @Override
    public void handle(
        Message event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        cache.getCacheForGuild(event.guildId()).remove(event.id(), event.getClass());
    }
}
