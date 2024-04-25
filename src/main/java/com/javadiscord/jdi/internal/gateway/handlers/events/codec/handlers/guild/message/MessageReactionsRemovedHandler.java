package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.Message;
import com.javadiscord.jdi.internal.models.message.MessageReactionsRemoved;

public class MessageReactionsRemovedHandler implements EventHandler<MessageReactionsRemoved> {
    @Override
    public void handle(
        MessageReactionsRemoved event,
        ConnectionMediator connectionMediator,
        Discord discord
    ) {
        if (discord.getCache().getCacheForGuild(event.guildId()).isCached(event.messageId(), Message.class)) {
            Message message = (Message) discord.getCache().getCacheForGuild(event.guildId())
                .get(event.messageId(), Message.class);
            message.messageReactions().clear();
        }
    }
}
