package com.javadiscord.jdi.internal.cache;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public interface CacheInterface<T> {
    T get(long id, Class<?> clazz);

    void add(long id, T object);

    void update(long id, T updated);

    void remove(long id, Class<?> clazz);

    boolean isCached(long id, Class<?> clazz);

    long size();

    default String toId(long id, Class<?> clazz) {
        return id + "-" + clazz.getName();
    }

    @SuppressWarnings("unchecked")
    default T update(T original, T updated) {
        try {
            Class<?> recordClass = original.getClass();
            Constructor<?> constructor = recordClass.getDeclaredConstructors()[0];

            Object[] currentValues = new Object[constructor.getParameterCount()];
            for (int i = 0; i < currentValues.length; i++) {
                Field field = recordClass.getDeclaredFields()[i];
                field.setAccessible(true);
                currentValues[i] = field.get(original);
            }

            return (T) constructor.newInstance(
                Stream.of(constructor.getParameters())
                    .map(
                        parameter -> {
                            try {
                                Field field
                                    = recordClass.getDeclaredField(
                                        parameter.getName()
                                    );
                                field.setAccessible(true);
                                Object value = field.get(updated);
                                if (value != null) {
                                    return value;
                                }
                                return currentValues[(int) Stream.of(
                                    constructor
                                        .getParameters()
                                )
                                    .filter(
                                        p -> p == parameter
                                    )
                                    .count()
                                    - 1];
                            } catch (
                                IllegalAccessException
                                | NoSuchFieldException e
                            ) {
                                throw new RuntimeException(e);
                            }
                        }
                    )
                    .toArray()
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
