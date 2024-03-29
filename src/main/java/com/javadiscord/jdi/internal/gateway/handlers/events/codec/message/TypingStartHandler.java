package com.javadiscord.jdi.internal.gateway.handlers.events.codec.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.TypingStart;

public class TypingStartHandler implements EventHandler<TypingStart> {
    @Override
    public void handle(TypingStart event, ConnectionMediator connectionMediator, Discord discord) {}
}
