package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

public class WebhookDecoder implements EventDecoder<WebhookDecoder> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public WebhookDecoder decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), WebhookDecoder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
