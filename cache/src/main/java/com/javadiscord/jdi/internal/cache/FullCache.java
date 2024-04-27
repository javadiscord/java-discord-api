package com.javadiscord.jdi.internal.cache;

import java.util.HashMap;
import java.util.Map;

public class FullCache implements CacheInterface<Object> {
    private final Map<String, Object> models = new HashMap<>();

    @Override
    public Object get(long id, Class<?> clazz) {
        return models.get(toId(id, clazz));
    }

    @Override
    public void add(long id, Object object) {
        String cacheId = toId(id, object.getClass());
        if (models.containsKey(cacheId)) {
            update(id, object);
        } else {
            models.put(cacheId, object);
        }
    }

    @Override
    public void update(long id, Object object) {
        if (models.containsKey(toId(id, object.getClass()))) {
            Object cached = models.get(toId(id, object.getClass()));
            Object updated = update(cached, object);
            models.replace(toId(id, object.getClass()), updated);
        }
    }

    @Override
    public boolean isCached(long id, Class<?> clazz) {
        return models.containsKey(toId(id, clazz));
    }

    @Override
    public long size() {
        return models.size();
    }

    @Override
    public void remove(long id, Class<?> clazz) {
        models.remove(toId(id, clazz));
    }
}
