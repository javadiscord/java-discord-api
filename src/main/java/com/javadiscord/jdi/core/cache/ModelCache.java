package com.javadiscord.jdi.core.cache;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelCache {
    private static final Logger LOGGER = LogManager.getLogger(ModelCache.class);
    private final Map<String, Object> models = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getFromCache(long id, Class<T> clazz) {
        return (T) models.get(asId(id, clazz));
    }

    public void delete(long id, Class<?> clazz) {
        models.remove(asId(id, clazz));
    }

    private <T> String asId(long id, T clazz) {
        return id + "|" + clazz.getClass().getName();
    }

    private <T> String asId(long id, Class<T> clazz) {
        return id + "|" + clazz.getName();
    }

    public <T> boolean isCached(long id, T clazz) {
        return models.containsKey(asId(id, clazz));
    }

    public void cache(long id, Object model) {
        String asId = asId(id, model);
        if (models.containsKey(asId)) {
            updateCachedModel(id, model);
        } else {
            models.put(asId, model);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void updateCachedModel(long id, T model) {
        T cached = (T) models.get(asId(id, model));

        try {
            Class<?> recordClass = cached.getClass();
            Constructor<?> constructor = recordClass.getDeclaredConstructors()[0];

            Object[] currentValues = new Object[constructor.getParameterCount()];
            for (int i = 0; i < currentValues.length; i++) {
                Field field = recordClass.getDeclaredFields()[i];
                field.setAccessible(true);
                currentValues[i] = field.get(cached);
            }

            // Create a new instance of the record with updated values from the model
            T updated = (T) constructor.newInstance(
                Stream.of(constructor.getParameters())
                    .map(parameter -> {
                        try {
                            Field field = recordClass.getDeclaredField(parameter.getName());
                            field.setAccessible(true);
                            Object value = field.get(model);
                            if (value != null) {
                                return value;
                            }
                            return currentValues[(int) Stream.of(constructor.getParameters())
                                .filter(p -> p == parameter)
                                .count() - 1];
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            LOGGER.error("Failed to update cache for model {}", id, e);
                            return null;
                        }
                    })
                    .toArray()
            );

            models.replace(asId(id, model), updated);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Failed to update cache for model {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
