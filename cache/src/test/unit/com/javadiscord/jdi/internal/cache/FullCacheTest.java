package com.javadiscord.jdi.internal.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FullCacheTest {
    @Test
    void testAddingItems() {
        FullCache cache = new FullCache();
        cache.add(1L, "hello world");
        cache.add(2L, 1);
        assertEquals(2, cache.size());
    }

    @Test
    void testRemovingItems() {
        FullCache cache = new FullCache();
        cache.add(1L, "dummy-1");
        cache.add(2L, "dummy-2");
        cache.remove(1L, String.class);
        assertEquals(1, cache.size());
    }

    @Test
    void testUpdatingItems() {
        record SampleItem(String value) {}

        FullCache cache = new FullCache();
        cache.add(1L, new SampleItem("dummy"));
        cache.update(1L, new SampleItem("hello world"));

        SampleItem retrieved = (SampleItem) cache.get(1L, SampleItem.class);

        assertEquals(1, cache.size());
        assertEquals("hello world", retrieved.value);
    }

    @Test
    void testIsCached() {
        record SampleItem(String value) {}
        FullCache cache = new FullCache();
        cache.add(1L, new SampleItem("dummy"));
        // cache.update(1L, new SampleItem("hello world"));
        assertTrue(cache.isCached(1L, SampleItem.class));
    }

    @Test
    void testSize() {
        record SampleItem(String value) {}
        FullCache cache = new FullCache();
        cache.add(1L, new SampleItem("dummy"));
        cache.add(2L, new SampleItem("dummy"));
        cache.add(3L, new SampleItem("dummy"));
        assertEquals(3, cache.size());
    }
}
