package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

public class GuildDecoder implements EventDecoder<Guild> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Guild decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), Guild.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
