package com.javadiscord.jdi.internal.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NoCacheTest {

    @Test
    void testNoItemsCanBeAdded() {
        NoCache cache = new NoCache();
        cache.add(1, "dummy-object");
        assertFalse(cache.isCached(1L, String.class));
        assertEquals(0, cache.size());
    }
}