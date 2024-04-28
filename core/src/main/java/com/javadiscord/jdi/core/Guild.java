package com.javadiscord.jdi.core;

import com.javadiscord.jdi.internal.cache.Cache;

public class Guild {
    private final com.javadiscord.jdi.core.models.guild.Guild metadata;
    private final Cache cache;
    private final Discord discord;

    public Guild(com.javadiscord.jdi.core.models.guild.Guild guild, Cache cache, Discord discord) {
        this.metadata = guild;
        this.cache = cache;
        this.discord = discord;
    }

    public com.javadiscord.jdi.core.models.guild.Guild getMetadata() {
        return metadata;
    }

    public Cache getCache() {
        return cache;
    }
}
