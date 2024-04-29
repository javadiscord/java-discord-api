package com.javadiscord.jdi.internal.gateway.handlers.events.codec;

import com.javadiscord.jdi.internal.gateway.handlers.events.EventType;

public interface GatewayObserver {
    void receive(EventType eventType, Object event);
}
