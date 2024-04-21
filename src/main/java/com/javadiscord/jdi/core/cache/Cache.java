package com.javadiscord.jdi.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
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
