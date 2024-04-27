package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.guild.MemberChunk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MemberChunkDecoder implements EventDecoder<MemberChunk> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public MemberChunk decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), MemberChunk.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
