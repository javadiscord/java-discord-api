package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.core.models.guild.IntegrationUpdate;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class IntegrationUpdateDecoder implements EventDecoder<IntegrationUpdate> {
    private static final ObjectMapper OBJECT_MAPPER =
        JsonMapper.builder().addModule(new JavaTimeModule()).build();

    @Override
    public IntegrationUpdate decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), IntegrationUpdate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
