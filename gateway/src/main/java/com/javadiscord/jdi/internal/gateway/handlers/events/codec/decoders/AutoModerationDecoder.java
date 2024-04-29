package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationRule;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

public class AutoModerationDecoder implements EventDecoder<AutoModerationRule> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public AutoModerationRule decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(
                    gatewayEvent.data().toString(), AutoModerationRule.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
