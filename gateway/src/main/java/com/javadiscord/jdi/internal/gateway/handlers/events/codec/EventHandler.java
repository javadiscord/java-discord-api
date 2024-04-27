package com.javadiscord.jdi.internal.gateway.handlers.events.codec;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;

public interface EventHandler<T> {
    void handle(T event, ConnectionMediator connectionMediator, Cache cache);
}
