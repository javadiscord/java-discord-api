package com.javadiscord.gateway.handlers.events.codec.message;

import com.javadiscord.Discord;
import com.javadiscord.discord.message.Message;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

public class MessageCreateHandler implements EventHandler<Message> {
    @Override
    public void handle(Message event, ConnectionMediator connectionMediator, Discord discord) {

    }
}
