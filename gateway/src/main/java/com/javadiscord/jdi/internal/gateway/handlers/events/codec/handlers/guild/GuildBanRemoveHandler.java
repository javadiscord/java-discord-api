package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild;

import com.javadiscord.jdi.core.models.guild.GuildBan;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class GuildBanRemoveHandler implements EventHandler<GuildBan> {
    @Override
    public void handle(GuildBan event, ConnectionMediator connectionMediator, Cache cache) {}
}
