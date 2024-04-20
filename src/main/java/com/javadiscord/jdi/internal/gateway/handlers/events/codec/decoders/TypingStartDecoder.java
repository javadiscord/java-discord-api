package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.message.TypingStart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TypingStartDecoder implements EventDecoder<TypingStart> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public TypingStart decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), TypingStart.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
