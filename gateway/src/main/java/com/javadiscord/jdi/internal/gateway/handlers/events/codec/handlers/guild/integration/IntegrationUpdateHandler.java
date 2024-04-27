package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.integration;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.IntegrationUpdate;

public class IntegrationUpdateHandler implements EventHandler<IntegrationUpdate> {
    @Override
    public void handle(
        IntegrationUpdate event, ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
