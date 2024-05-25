package com.javadiscord.jdi.core.api.utils;

import java.lang.reflect.Field;
import java.util.List;

import com.javadiscord.jdi.internal.cache.Cache;

public class CacheUpdater {

    private final Cache cache;

    public CacheUpdater(Cache cache) {
        this.cache = cache;
    }

    public <T> void updateCache(T result) {
        if (result == null) {
            return;
        }
        try {
            Field guildIdField = result.getClass().getDeclaredField("guildId");
            Field idField = result.getClass().getDeclaredField("id");

            long guildId = getLongFromField(guildIdField, result);
            long id = getLongFromField(idField, result);

            cache.getCacheForGuild(guildId).add(id, result);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // NoSuchFieldException: don't cache result
        }
    }

    public <T> void updateCache(List<T> resultList) {
        resultList.forEach(this::updateCache);
    }

    private <T> long getLongFromField(Field field, T result) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == String.class) {
            return Long.parseLong((String) field.get(result));
        }
        return (long) field.get(result);
    }
}
