package com.javadiscord.jdi.internal.cache;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.javadiscord.jdi.core.models.guild.GuildModel;

import org.junit.jupiter.api.Test;

class CacheTest {

    @Test
    void testFetchingGuildFromCache() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.createCache(guildMock.id());

        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
    }

    @Test
    void testFetchingGuildFromCacheWithNoCache() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.NO_CACHE);
        cache.createCache(guildMock.id());
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
    }

    @Test
    void testFetchingGuildFromCacheWithPartialCache() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.PARTIAL);
        cache.createCache(guildMock.id());
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
    }

    @Test
    void testFetchingFromFullCache() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.createCache(guildMock.id());

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        cacheInterface.add(100L, "Hello");
        cacheInterface.add(200L, 123);

        String cachedString = (String) cacheInterface.get(100L, String.class);
        int cachedInt = (int) cacheInterface.get(200L, Integer.class);

        assertNotNull(cacheInterface);
        assertEquals(2, cacheInterface.size());
        assertEquals("Hello", cachedString);
        assertEquals(123, cachedInt);
    }

    @Test
    void testFetchingFromFullCacheWhenItemNotPresent() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.createCache(guildMock.id());

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        String cachedString = (String) cacheInterface.get(100L, String.class);

        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
        assertNull(cachedString);
    }

    @Test
    void testRemovingGuildFromCache() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.createCache(guildMock.id());

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);

        cache.removeGuild(guildMock.id());
        assertNull(cache.getCacheForGuild(1L));
    }

    @Test
    void testGetCacheForType() {
        GuildModel guildMock = mock(GuildModel.class);
        when(guildMock.id()).thenReturn(1L);

        {
            Cache cache = new Cache(CacheType.FULL);
            cache.createCache(guildMock.id());
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(FullCache.class, cacheInterface);
        }

        {
            Cache cache = new Cache(CacheType.PARTIAL);
            cache.createCache(guildMock.id());
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(PartialCache.class, cacheInterface);
        }

        {
            Cache cache = new Cache(CacheType.NO_CACHE);
            cache.createCache(guildMock.id());
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(NoCache.class, cacheInterface);
        }
    }
}
