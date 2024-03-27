package com.javadiscord.gateway.handlers.events.ready;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.events.EventListener;

public class ReadyEventListener implements EventListener {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onEvent(
            GatewayEvent gatewayEvent, ConnectionMediator connectionMediator, Discord discord) {
        try {
            ReadyMessage readyMessage =
                    OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), ReadyMessage.class);
            connectionMediator
                    .getConnectionDetails()
                    .setGatewayURL(readyMessage.resumeGatewayURL());
            connectionMediator.getConnectionDetails().setSessionId(readyMessage.sessionId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
