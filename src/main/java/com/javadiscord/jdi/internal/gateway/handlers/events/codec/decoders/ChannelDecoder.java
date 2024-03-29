package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.models.channel.Channel;

public class ChannelDecoder implements EventDecoder<Channel> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Channel decode(GatewayEvent gatewayEvent) {
        try {
            return OBJECT_MAPPER.readValue(gatewayEvent.data().toString(), Channel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
