package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.message.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDecoder implements EventDecoder<Message> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Message decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
