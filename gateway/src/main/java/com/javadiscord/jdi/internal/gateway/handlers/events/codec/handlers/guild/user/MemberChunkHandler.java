package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user;

import com.javadiscord.jdi.core.models.guild.MemberChunk;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class MemberChunkHandler implements EventHandler<MemberChunk> {
    @Override
    public void handle(MemberChunk event, ConnectionMediator connectionMediator, Cache cache) {
        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(event.guildId());
        for (Member member : event.members()) {
            User user = member.user();
            cacheInterface.update(user.id(), user);
        }
    }
}
