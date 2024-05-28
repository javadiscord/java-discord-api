package com.javadiscord.jdi.core.processor.validator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.javadiscord.jdi.core.annotations.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComponentValidator {
    private static final Logger LOGGER = LogManager.getLogger(ComponentValidator.class);

    public boolean validate(Class<?> clazz) {
        return validateMethods(clazz);
    }

    private boolean validateMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Component.class)) {
                if (method.getParameterCount() != 0) {
                    LOGGER.error("Methods annotated with @Component requires 0 parameters");
                    return false;
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    LOGGER.error("Methods annotated with @Component must be static");
                    return false;
                }
            }
        }
        return true;
    }

}
