package com.javadiscord.jdi.internal.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartialCache implements CacheInterface<Object> {
    private final Map<String, Object> models = new HashMap<>();
    private final List<String> classes;

    public PartialCache(List<String> classes) {
        this.classes = classes;
    }

    @Override
    public Object get(long id, Class<?> clazz) {
        return models.get(toId(id, clazz));
    }

    @Override
    public void add(long id, Object object) {
        if (classes.contains(object.getClass().getName())) {
            models.put(toId(id, object.getClass()), object);
        }
    }

    @Override
    public void update(long id, Object object) {
        if (classes.contains(object.getClass().getName())) {
            Object cached = models.get(toId(id, object.getClass()));
            Object updated = update(cached, object);
            models.replace(toId(id, object.getClass()), updated);
        } else {
            add(id, object);
        }
    }

    @Override
    public void remove(long id, Class<?> clazz) {
        models.remove(toId(id, clazz));
    }

    @Override
    public boolean isCached(long id, Class<?> clazz) {
        return models.containsKey(toId(id, clazz));
    }

    @Override
    public long size() {
        return models.size();
    }
}
