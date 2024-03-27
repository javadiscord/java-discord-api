package com.javadiscord.gateway.handlers.events.codec.guild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.discord.Guild;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;

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
