package com.javadiscord.jdi.internal;

public interface ReflectiveSlashCommandLoader {
    Object getSlashCommandClassMethod(String name);

    void injectComponents(Object object);
}
