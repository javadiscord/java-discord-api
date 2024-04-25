package com.javadiscord.jdi.core.cache;

import com.javadiscord.jdi.internal.models.guild.Guild;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CacheTest {

    @Test
    void testFetchingGuildFromCache() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.cacheGuild(guildMock);

        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(1, cacheInterface.size());
    }

    @Test
    void testFetchingGuildFromCacheWithNoCache() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.NO_CACHE);
        cache.cacheGuild(guildMock);
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
    }

    @Test
    void testFetchingGuildFromCacheWithPartialCache() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.PARTIAL);
        cache.cacheGuild(guildMock);
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);
        assertEquals(0, cacheInterface.size());
    }

    @Test
    void testFetchingFromFullCache() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.cacheGuild(guildMock);

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        cacheInterface.add(100L, "Hello");
        cacheInterface.add(200L, 123);

        String cachedString = (String) cacheInterface.get(100L, String.class);
        int cachedInt = (int) cacheInterface.get(200L, Integer.class);

        assertNotNull(cacheInterface);
        assertEquals(3, cacheInterface.size());
        assertEquals("Hello", cachedString);
        assertEquals(123, cachedInt);
    }

    @Test
    void testFetchingFromFullCacheWhenItemNotPresent() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.cacheGuild(guildMock);

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        String cachedString = (String) cacheInterface.get(100L, String.class);

        assertNotNull(cacheInterface);
        assertEquals(1, cacheInterface.size());
        assertNull(cachedString);
    }

    @Test
    void testRemovingGuildFromCache() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        Cache cache = new Cache(CacheType.FULL);
        cache.cacheGuild(guildMock);

        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
        assertNotNull(cacheInterface);

        cache.removeGuild(guildMock);
        assertNull(cache.getCacheForGuild(1L));
    }

    @Test
    void testGetCacheForType() {
        Guild guildMock = mock(Guild.class);
        when(guildMock.id()).thenReturn(1L);

        {
            Cache cache = new Cache(CacheType.FULL);
            cache.cacheGuild(guildMock);
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(FullCache.class, cacheInterface);
        }

        {
            Cache cache = new Cache(CacheType.PARTIAL);
            cache.cacheGuild(guildMock);
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(PartialCache.class, cacheInterface);
        }

        {
            Cache cache = new Cache(CacheType.NO_CACHE);
            cache.cacheGuild(guildMock);
            CacheInterface<Object> cacheInterface = cache.getCacheForGuild(1L);
            assertInstanceOf(NoCache.class, cacheInterface);
        }
    }

}