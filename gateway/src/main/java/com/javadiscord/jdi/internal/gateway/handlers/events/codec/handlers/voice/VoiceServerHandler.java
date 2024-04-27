package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.voice;

import com.javadiscord.jdi.core.models.voice.VoiceServer;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class VoiceServerHandler implements EventHandler<VoiceServer> {
    @Override
    public void handle(VoiceServer event, ConnectionMediator connectionMediator, Cache cache) {}
}
