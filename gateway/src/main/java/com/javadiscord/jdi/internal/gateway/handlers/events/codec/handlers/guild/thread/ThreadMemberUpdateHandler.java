package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMemberUpdate;

public class ThreadMemberUpdateHandler implements EventHandler<ThreadMemberUpdate> {
    @Override
    public void handle(
            ThreadMemberUpdate event, ConnectionMediator connectionMediator, Cache cache) {}
}
