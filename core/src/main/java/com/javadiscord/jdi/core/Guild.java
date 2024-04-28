package com.javadiscord.jdi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.core.request.Channel;
import com.javadiscord.jdi.internal.cache.Cache;

public class Guild {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final com.javadiscord.jdi.core.models.guild.Guild metadata;

    public Guild(com.javadiscord.jdi.core.models.guild.Guild guild, Cache cache, Discord discord) {
        this.metadata = guild;
    }

    private final Channel channel = new Channel();

    public Channel channel() {
        return channel;
    }

    public com.javadiscord.jdi.core.models.guild.Guild getMetadata() {
        return metadata;
    }
}
