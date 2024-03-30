package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.user.Member;

public class GuildMemberUpdateHandler implements EventHandler<Member> {
    @Override
    public void handle(Member event, ConnectionMediator connectionMediator, Discord discord) {}
}
