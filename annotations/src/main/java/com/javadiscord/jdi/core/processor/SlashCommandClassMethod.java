package com.javadiscord.jdi.core.processor;

import java.lang.reflect.Method;

public record SlashCommandClassMethod(Class<?> clazz, Method method) {}
