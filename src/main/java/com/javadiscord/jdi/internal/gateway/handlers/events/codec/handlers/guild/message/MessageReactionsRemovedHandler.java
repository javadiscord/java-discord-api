package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessageReactionsRemoved;

public class MessageReactionsRemovedHandler implements EventHandler<MessageReactionsRemoved> {
    @Override
    public void handle(
            MessageReactionsRemoved event,
            ConnectionMediator connectionMediator,
            Discord discord) {}
}
