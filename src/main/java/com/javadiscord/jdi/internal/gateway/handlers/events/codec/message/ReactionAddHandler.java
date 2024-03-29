package com.javadiscord.jdi.internal.gateway.handlers.events.codec.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessageReaction;

public class ReactionAddHandler implements EventHandler<MessageReaction> {
    @Override
    public void handle(
            MessageReaction event, ConnectionMediator connectionMediator, Discord discord) {}
}
