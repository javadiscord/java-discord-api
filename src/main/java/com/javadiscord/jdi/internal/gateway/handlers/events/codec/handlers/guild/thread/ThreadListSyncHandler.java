package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadSync;

public class ThreadListSyncHandler implements EventHandler<ThreadSync> {
    @Override
    public void handle(ThreadSync event, ConnectionMediator connectionMediator, Discord discord) {}
}
