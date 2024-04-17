package com.javadiscord.jdi.core.processor.validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.javadiscord.jdi.core.annotations.ChannelCreate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventListenerValidator {
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean validate(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        if (constructors.length > 0) {
            Constructor<?> mainConstructor = null;
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() <= 0) {
                    mainConstructor = constructor;
                    break;
                }
            }
            if (mainConstructor == null) {
                LOGGER.error("{} does not have a 0 arg constructor", clazz.getName());
                return false;
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ChannelCreate.class)) {
                    if (method.getParameterCount() > 0) {
                        Class<?>[] paramTypes = method.getParameterTypes();
                        for (Class<?> type : paramTypes) {
                            if (!(type.getName().endsWith("Channel")
                                    || type.getName().endsWith("Discord"))) {
                                LOGGER.error("Unexpected parameter found: {}", type.getName());
                                return false;
                            }
                        }
                    } else {
                        LOGGER.error(method.getName() + " does not have"
                                + " `com.javadiscord.jdi.internal.models.Channel`" + " parameter");
                    }
                }
            }
        }
        return true;
    }
}
