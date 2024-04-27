package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class GuildInviteCreateHandler implements EventHandler<Invite> {
    @Override
    public void handle(Invite event, ConnectionMediator connectionMediator, Cache cache) {}
}
