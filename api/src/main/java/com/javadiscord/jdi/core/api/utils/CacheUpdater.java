package com.javadiscord.jdi.core.api.utils;

import java.lang.reflect.Field;
import java.util.List;

import com.javadiscord.jdi.internal.cache.Cache;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheUpdater {

    private final Cache cache;

    private static final Logger LOGGER = LogManager.getLogger(CacheUpdater.class);

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

            if (cache.getCacheForGuild(guildId) == null) {
                LOGGER.log(
                    Level.TRACE, "Failed to cache result of type {} with guildId of {}",
                    result.getClass().getName(), guildId
                );
            } else {
                cache.getCacheForGuild(guildId).add(id, result);
            }

        } catch (IllegalAccessException | NoSuchFieldException | NumberFormatException e) {
            LOGGER.log(
                Level.TRACE, "Failed to cache result of type {}, cause: {}",
                result.getClass().getName(), e.getMessage()
            );
        }
    }

    public <T> void updateCache(List<T> resultList) {
        resultList.forEach(this::updateCache);
    }

    private <T> long getLongFromField(
        Field field,
        T result
    ) throws IllegalAccessException, NumberFormatException {
        field.setAccessible(true);
        if (field.getType() == String.class) {
            try {
                return Long.parseLong((String) field.get(result));
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                    String.format(
                        "Failed to parse %s=%s as a long: ", field.getName(), field.get(result)
                    )
                );
            }
        }
        return (long) field.get(result);
    }
}
