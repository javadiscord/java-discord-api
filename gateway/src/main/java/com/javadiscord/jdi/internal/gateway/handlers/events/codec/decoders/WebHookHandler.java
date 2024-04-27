package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.webhook.Webhook;

public class WebHookHandler implements EventHandler<Webhook> {
    @Override
    public void handle(
        Webhook event, ConnectionMediator connectionMediator,
        Cache cache
    ) {}
}
