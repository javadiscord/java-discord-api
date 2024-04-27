package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.voice;

import com.javadiscord.jdi.core.models.voice.VoiceState;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class VoiceStateHandler implements EventHandler<VoiceState> {
    @Override
    public void handle(VoiceState event, ConnectionMediator connectionMediator, Cache cache) {}
}
