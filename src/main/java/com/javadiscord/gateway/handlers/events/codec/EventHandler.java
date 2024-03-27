package com.javadiscord.gateway.handlers.events.codec;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;

public interface EventHandler<T> {
    void handle(T event, ConnectionMediator connectionMediator, Discord discord);
}
