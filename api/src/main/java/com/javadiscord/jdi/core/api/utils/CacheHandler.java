package com.javadiscord.jdi.core.api.utils;

import com.javadiscord.jdi.internal.cache.Cache;

import java.lang.reflect.Field;
import java.util.List;

public class CacheHandler {

    private final Cache cache;

    public CacheHandler(Cache cache) {
        this.cache = cache;
    }

    public <T> void cacheResult(T result) {
        try {
            Field guildIdField = result.getClass().getDeclaredField("guildId");
            Field idField = result.getClass().getDeclaredField("id");

            long guildId = getLongFromField(guildIdField);
            long id = getLongFromField(idField);

            cache.getCacheForGuild(guildId).add(id, result);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // NoSuchFieldException means we don't want to cache result
        }
    }

    public <T> void cacheResult(List<T> resultList) {
        resultList.forEach(this::cacheResult);
    }

    private long getLongFromField(Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == String.class) {
            return Long.parseLong((String) field.get(field.getName()));
        }
        return (long) field.get(field.getName());
    }
}
