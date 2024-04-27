package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessagePin;

public class ChannelPinUpdateHandler implements EventHandler<MessagePin> {
    @Override
    public void handle(
        MessagePin event, ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
