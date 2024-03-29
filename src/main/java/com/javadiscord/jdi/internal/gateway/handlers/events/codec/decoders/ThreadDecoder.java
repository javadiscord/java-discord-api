package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.channel.Thread;

public class ThreadDecoder implements EventDecoder<Thread> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Thread decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), Thread.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
