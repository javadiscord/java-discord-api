package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Invite;

public class GuildInviteDeleteHandler implements EventHandler<Invite> {
    @Override
    public void handle(Invite event, ConnectionMediator connectionMediator, Discord discord) {}
}
