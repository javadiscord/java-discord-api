package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMember;

public class ThreadMemberHandler implements EventHandler<ThreadMember> {
    @Override
    public void handle(ThreadMember event, ConnectionMediator connectionMediator, Discord discord) {
    }
}
