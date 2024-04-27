package com.javadiscord.jdi.internal.cache;

import java.util.Optional;

public class NoCache implements CacheInterface<Object> {

    @Override
    public Optional<Object> get(long id, Class<?> clazz) {
        return Optional.empty();
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

    @Override
    public long size() {
        return 0;
    }
}
