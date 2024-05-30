package com.javadiscord.jdi.internal.processor;

import java.lang.reflect.Method;

public record SlashCommandClassMethod(Class<?> clazz, Method method) {}
