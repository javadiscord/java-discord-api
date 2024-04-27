package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.TypingStart;

public class TypingStartHandler implements EventHandler<TypingStart> {
    @Override
    public void handle(TypingStart event, ConnectionMediator connectionMediator, Cache cache) {}
}
