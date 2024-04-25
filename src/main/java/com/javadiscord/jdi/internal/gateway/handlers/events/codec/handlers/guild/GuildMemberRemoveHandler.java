package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.user.Member;
import com.javadiscord.jdi.internal.models.user.User;

public class GuildMemberRemoveHandler implements EventHandler<Member> {
    @Override
    public void handle(Member event, ConnectionMediator connectionMediator, Discord discord) {
        discord.getCache().getCacheForGuild(event.guildId()).remove(event.user().id(), User.class);
    }
}
