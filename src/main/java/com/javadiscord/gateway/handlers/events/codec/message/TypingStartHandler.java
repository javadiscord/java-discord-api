package com.javadiscord.gateway.handlers.events.codec.message;

import com.javadiscord.Discord;
import com.javadiscord.discord.message.TypingStart;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

public class TypingStartHandler implements EventHandler<TypingStart> {
    @Override
    public void handle(TypingStart event, ConnectionMediator connectionMediator, Discord discord) {

    }
}
