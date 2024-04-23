package com.javadiscord.jdi.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.internal.models.guild.Guild;

public class Cache {
    @SuppressWarnings("rawtypes")
    private final Map<Long, CacheInterface> cache;
    private final CacheType cacheType;

    public Cache(CacheType cacheType) {
        this.cacheType = cacheType;
        this.cache = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public CacheInterface<Object> getCacheForGuild(long guildId) {
        if (!cache.containsKey(guildId)) {
            return cache.put(guildId, getCacheForType());
        }
        return cache.get(guildId);
    }

    @SuppressWarnings("rawtypes")
    public Map<Long, CacheInterface> getCachedGuilds() {
        return cache;
    }

    @SuppressWarnings("unchecked")
    public void cacheGuild(Guild guild) {
        CacheInterface cacheInterface = getCacheForType();
        cacheInterface.add(guild.id(), guild);
        cache.put(guild.id(), cacheInterface);
    }

    public void removeGuild(Guild guild) {
        cache.remove(guild.id());
    }

    private CacheInterface<?> getCacheForType() {
        switch (cacheType) {
            case PARTIAL -> {
                return new PartialCache(List.of());
            }
            case NO_CACHE -> {
                return new NoCache();
            }
            default -> {
                return new FullCache();
            }
        }
    }
}
