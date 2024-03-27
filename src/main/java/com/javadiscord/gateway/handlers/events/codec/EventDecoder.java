package com.javadiscord.gateway.handlers.events.codec;

import com.javadiscord.gateway.GatewayEvent;

public interface EventDecoder<T> {
    T decode(GatewayEvent gatewayEvent);
}
