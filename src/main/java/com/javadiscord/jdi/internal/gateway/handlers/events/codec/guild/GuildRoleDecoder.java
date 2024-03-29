package com.javadiscord.jdi.internal.gateway.handlers.events.codec.guild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.guild.Role;

public class GuildRoleDecoder implements EventDecoder<Role> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Role decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), Role.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
