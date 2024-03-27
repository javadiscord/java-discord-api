package com.javadiscord.gateway.handlers.events.codec.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.discord.message.TypingStart;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;

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
