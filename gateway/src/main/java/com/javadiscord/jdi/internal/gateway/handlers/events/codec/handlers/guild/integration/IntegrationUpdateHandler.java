package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.integration;

import com.javadiscord.jdi.core.models.guild.IntegrationUpdate;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class IntegrationUpdateHandler implements EventHandler<IntegrationUpdate> {
    @Override
    public void handle(
        IntegrationUpdate event,
        ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
