package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.core.models.message.MessageBulkDelete;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageBulkDeleteDecoder implements EventDecoder<MessageBulkDelete> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public MessageBulkDelete decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), MessageBulkDelete.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
