package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.message.StickerUpdate;

public class StickerUpdateDecoder implements EventDecoder<StickerUpdate> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public StickerUpdate decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), StickerUpdate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
