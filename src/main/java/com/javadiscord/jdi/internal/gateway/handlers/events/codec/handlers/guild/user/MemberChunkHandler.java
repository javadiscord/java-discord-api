package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.MemberChunk;
import com.javadiscord.jdi.internal.models.user.Member;
import com.javadiscord.jdi.internal.models.user.User;

public class MemberChunkHandler implements EventHandler<MemberChunk> {
    @Override
    public void handle(MemberChunk event, ConnectionMediator connectionMediator, Discord discord) {
        CacheInterface<Object> cacheInterface = discord.getCache().getCacheForGuild(event.guildId());
        for (Member member : event.members()) {
            User user = member.user();
            cacheInterface.update(user.id(), user);
        }
    }
}
