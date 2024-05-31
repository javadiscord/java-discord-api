package com.javadiscord.jdi.internal;

import java.lang.reflect.Method;

public interface ReflectiveSlashCommandClassMethod {
    Class<?> clazz();

    Method method();

    Object instance();
}
