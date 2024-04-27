package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.ready;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.ready.ReadyEvent;

public class ReadyEventHandler implements EventHandler<ReadyEvent> {

    @Override
    public void handle(
        ReadyEvent message, ConnectionMediator connectionMediator,
        Cache cache
    ) {
        connectionMediator.getConnectionDetails().setSessionId(message.sessionId());
        connectionMediator.getConnectionDetails().setGatewayURL(message.resumeGatewayURL());
    }
}
