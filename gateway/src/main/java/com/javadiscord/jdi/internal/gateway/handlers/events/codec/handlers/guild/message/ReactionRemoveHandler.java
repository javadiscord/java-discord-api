package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessageReaction;

public class ReactionRemoveHandler implements EventHandler<MessageReaction> {
    @Override
    public void handle(MessageReaction event, ConnectionMediator connectionMediator, Cache cache) {}
}
