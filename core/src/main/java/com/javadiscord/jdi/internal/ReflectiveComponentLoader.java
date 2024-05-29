package com.javadiscord.jdi.internal;

public interface ReflectiveComponentLoader {
    void loadComponents();

    void injectComponents(Object component);
}
