package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.channel.ThreadMemberUpdate;

public class ThreadMemberUpdateHandler implements EventHandler<ThreadMemberUpdate> {
    @Override
    public void handle(
            ThreadMemberUpdate event, ConnectionMediator connectionMediator, Discord discord) {}
}
