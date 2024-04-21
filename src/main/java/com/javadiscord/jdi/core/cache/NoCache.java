package com.javadiscord.jdi.core.cache;

public class NoCache implements CacheInterface<Object> {

    @Override
    public Object get(long id, Class<?> clazz) {
        return null;
    }

    @Override
    public void add(long id, Object object) {

    }

    @Override
    public void update(long id, Object updated) {

    }

    @Override
    public void remove(long id, Class<?> clazz) {

    }

    @Override
    public boolean isCached(long id, Class<?> clazz) {
        return false;
    }
}
