package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Role;

public class GuildRoleUpdateHandler implements EventHandler<Role> {
    @Override
    public void handle(Role event, ConnectionMediator connectionMediator, Discord discord) {}
}
