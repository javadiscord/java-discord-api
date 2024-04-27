package com.javadiscord.jdi.internal.cache;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PartialCacheTest {

    @Test
    void testAddingAllowedItems() {
        PartialCache cache = new PartialCache(List.of("java.lang.String"));
        cache.add(1L, "hello world");
        cache.add(2L, 1);
        assertEquals(1, cache.size());
    }

    @Test
    void testAddingDisallowedItems() {
        PartialCache cache = new PartialCache(List.of("java.lang.String"));
        cache.add(1L, 1);
        cache.add(2L, 2);
        assertEquals(0, cache.size());
    }

    @Test
    void testRemovingItems() {
        PartialCache cache = new PartialCache(List.of("java.lang.String"));
        cache.add(1L, "dummy-1");
        cache.add(2L, "dummy-2");
        cache.remove(1L, String.class);
        assertEquals(1, cache.size());
    }

    @Test
    void testUpdatingItems() {
        record SampleItem(String value) {}

        PartialCache cache = new PartialCache(List.of(SampleItem.class.getName()));
        cache.add(1L, new SampleItem("dummy"));
        cache.update(1L, new SampleItem("hello world"));

        SampleItem retrieved = (SampleItem) cache.get(1L, SampleItem.class);

        assertEquals(1, cache.size());
        assertEquals("hello world", retrieved.value);
    }

    @Test
    void testIsCached() {
        record SampleItem(String value) {}
        PartialCache cache = new PartialCache(List.of(SampleItem.class.getName()));
        cache.add(1L, new SampleItem("dummy"));
        cache.update(1L, new SampleItem("hello world"));
        assertTrue(cache.isCached(1L, SampleItem.class));
    }

    @Test
    void testSize() {
        record SampleItem(String value) {}
        PartialCache cache = new PartialCache(List.of(SampleItem.class.getName()));
        cache.add(1L, new SampleItem("dummy"));
        cache.add(2L, new SampleItem("dummy"));
        cache.add(3L, new SampleItem("dummy"));
        assertEquals(3, cache.size());
    }
}