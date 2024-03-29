package com.javadiscord.jdi.internal.gateway.handlers.events.codec.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.Message;

public class MessageCreateHandler implements EventHandler<Message> {
    @Override
    public void handle(Message event, ConnectionMediator connectionMediator, Discord discord) {}
}
