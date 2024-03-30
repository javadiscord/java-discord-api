package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.voice;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.voice.VoiceState;

public class VoiceStateHandler implements EventHandler<VoiceState> {
    @Override
    public void handle(VoiceState event, ConnectionMediator connectionMediator, Discord discord) {}
}
