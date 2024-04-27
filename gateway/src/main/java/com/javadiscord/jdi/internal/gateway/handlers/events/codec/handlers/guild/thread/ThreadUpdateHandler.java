package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread;

public class ThreadUpdateHandler implements EventHandler<Thread> {

    @Override
    public void handle(
        Thread event, ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
