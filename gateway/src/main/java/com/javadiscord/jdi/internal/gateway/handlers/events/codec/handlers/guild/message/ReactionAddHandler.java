package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.models.message.MessageReaction;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class ReactionAddHandler implements EventHandler<MessageReaction> {
    @Override
    public void handle(MessageReaction event, ConnectionMediator connectionMediator, Cache cache) {}
}
