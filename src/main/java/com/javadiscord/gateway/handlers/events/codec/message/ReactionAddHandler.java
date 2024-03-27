package com.javadiscord.gateway.handlers.events.codec.message;

import com.javadiscord.Discord;
import com.javadiscord.discord.message.MessageReaction;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

public class ReactionAddHandler implements EventHandler<MessageReaction> {
    @Override
    public void handle(
            MessageReaction event, ConnectionMediator connectionMediator, Discord discord) {}
}
