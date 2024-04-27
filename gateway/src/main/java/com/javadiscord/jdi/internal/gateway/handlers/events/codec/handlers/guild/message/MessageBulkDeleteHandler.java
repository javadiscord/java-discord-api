package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessageBulkDelete;

public class MessageBulkDeleteHandler implements EventHandler<MessageBulkDelete> {
    @Override
    public void handle(
        MessageBulkDelete event, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        for (long messageId : event.ids()) {
            cache.getCacheForGuild(event.guildId()).remove(messageId, event.getClass());
        }
    }
}
