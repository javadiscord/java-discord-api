package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.MessageReactionsRemoved;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class MessageReactionsRemovedHandler implements EventHandler<MessageReactionsRemoved> {
    @Override
    public void handle(
        MessageReactionsRemoved event,
        ConnectionMediator connectionMediator,
        Cache cache
    ) {
        if (cache.getCacheForGuild(event.guildId()).isCached(event.messageId(), Message.class)) {
            Message message =
                (Message) cache.getCacheForGuild(event.guildId())
                    .get(event.messageId(), Message.class);
            message.messageReactions().clear();
        }
    }
}
