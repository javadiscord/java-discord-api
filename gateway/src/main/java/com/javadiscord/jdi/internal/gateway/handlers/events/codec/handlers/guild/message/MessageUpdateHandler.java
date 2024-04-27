package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class MessageUpdateHandler implements EventHandler<Message> {
    @Override
    public void handle(Message event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).update(event.id(), event);
    }
}
