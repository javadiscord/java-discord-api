package com.javadiscord.jdi.internal.gateway.handlers.events.codec.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.user.User;

public class UserDecoder implements EventDecoder<User> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public User decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
