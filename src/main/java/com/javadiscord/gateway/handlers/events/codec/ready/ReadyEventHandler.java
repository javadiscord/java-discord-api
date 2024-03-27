package com.javadiscord.gateway.handlers.events.codec.ready;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadyEventHandler implements EventHandler<ReadyEvent> {

    @Override
    public void handle(ReadyEvent message, ConnectionMediator connectionMediator, Discord discord) {
        connectionMediator.getConnectionDetails().setSessionId(message.sessionId());
        connectionMediator.getConnectionDetails().setGatewayURL(message.resumeGatewayURL());
    }
}
