package com.javadiscord.jdi.internal.gateway.handlers.events.codec.thread;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.channel.Thread;

public class ThreadDeleteHandler implements EventHandler<Thread> {

    @Override
    public void handle(Thread event, ConnectionMediator connectionMediator, Discord discord) {}
}
