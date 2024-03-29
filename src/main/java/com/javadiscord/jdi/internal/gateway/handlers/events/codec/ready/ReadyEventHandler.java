package com.javadiscord.jdi.internal.gateway.handlers.events.codec.ready;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class ReadyEventHandler implements EventHandler<ReadyEvent> {

    @Override
    public void handle(ReadyEvent message, ConnectionMediator connectionMediator, Discord discord) {
        connectionMediator.getConnectionDetails().setSessionId(message.sessionId());
        connectionMediator.getConnectionDetails().setGatewayURL(message.resumeGatewayURL());
    }
}
