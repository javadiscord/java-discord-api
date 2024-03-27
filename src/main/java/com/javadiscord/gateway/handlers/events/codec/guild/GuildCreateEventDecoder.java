package com.javadiscord.gateway.handlers.events.codec.guild;

import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.gateway.handlers.events.codec.ready.Guild;

public class GuildCreateEventDecoder implements EventDecoder<Guild> {

    @Override
    public Guild decode(GatewayEvent event) {
        return null;
    }
}
