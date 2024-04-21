package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.MemberChunk;

public class MemberChunkHandler implements EventHandler<MemberChunk> {
    @Override
    public void handle(MemberChunk event, ConnectionMediator connectionMediator, Discord discord) {

    }
}
