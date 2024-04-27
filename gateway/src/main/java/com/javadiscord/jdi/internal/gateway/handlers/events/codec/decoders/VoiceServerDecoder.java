package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.core.models.voice.VoiceServer;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

public class VoiceServerDecoder implements EventDecoder<VoiceServer> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public VoiceServer decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), VoiceServer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
