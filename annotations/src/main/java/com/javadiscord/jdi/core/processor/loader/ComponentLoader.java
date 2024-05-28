package com.javadiscord.jdi.core.processor.loader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.annotations.Component;
import com.javadiscord.jdi.core.annotations.Inject;
import com.javadiscord.jdi.core.processor.ClassFileUtil;
import com.javadiscord.jdi.core.processor.validator.ComponentValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComponentLoader {
    private static final Logger LOGGER = LogManager.getLogger(ComponentLoader.class);
    private static final Map<Class<?>, Object> COMPONENTS = new HashMap<>();
    private final ComponentValidator componentValidator = new ComponentValidator();

    public ComponentLoader() {
        try {
            loadComponents();
        } catch (Exception e) {
            LOGGER.error("An error occurred while loading components classes", e);
        }
    }

    private void loadComponents() {
        List<File> classes = ClassFileUtil.getClassesInClassPath();
        for (File classFile : classes) {
            try {
                Class<?> clazz = Class.forName(ClassFileUtil.getClassName(classFile));
                if (componentValidator.validate(clazz)) {
                    for (Method method : clazz.getMethods()) {
                        if (method.isAnnotationPresent(Component.class)) {
                            if (!COMPONENTS.containsKey(method.getReturnType())) {
                                COMPONENTS.put(method.getReturnType(), method.invoke(null));
                                LOGGER
                                    .info("Loaded component {}", method.getReturnType().getName());
                            } else {
                                LOGGER.error(
                                    "Component {} already loaded", method.getReturnType().getName()
                                );
                            }
                        }
                    }
                } else {
                    LOGGER.error("{} failed validation", clazz.getName());
                }
            } catch (Exception | Error ignore) {
                /* Ignore */
            }
        }
    }

    public static void injectComponents(Object component) {
        try {
            Class<?> clazz = component.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    if (COMPONENTS.containsKey(field.getType())) {
                        Object dependency = COMPONENTS.get(field.getType());
                        if (dependency != null) {
                            field.setAccessible(true);
                            try {
                                field.set(component, dependency);
                                LOGGER.info(
                                    "Injected component {} into {}",
                                    dependency.getClass().getName(), field.getType()
                                );
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(
                                    "Failed to inject dependency into field: " + field.getName(),
                                    e
                                );
                            }
                        }
                    } else {
                        LOGGER.error(
                            "No object {} was found in field {}", field.getType(), field.getName()
                        );
                    }
                }
            }
        } catch (Exception | Error ignore) {
            /* Ignore */
        }
    }
}
