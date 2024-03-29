package com.javadiscord.jdi.internal.gateway.handlers.events.codec;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;

public interface EventDecoder<T> {
    T decode(GatewayEvent gatewayEvent);
}
