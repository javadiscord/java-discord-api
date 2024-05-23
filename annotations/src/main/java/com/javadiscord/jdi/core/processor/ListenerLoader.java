package com.javadiscord.jdi.core.processor;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.List;

import com.javadiscord.jdi.core.annotations.EventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerLoader {
    private static final Logger LOGGER = LogManager.getLogger(ListenerLoader.class);
    private final EventListenerValidator eventListenerValidator = new EventListenerValidator();
    private final List<Object> eventListeners;

    public ListenerLoader(List<Object> eventListeners) {
        this.eventListeners = eventListeners;
        try {
            loadListeners();
        } catch (Exception e) {
            LOGGER.error("An error occurred while loading annotated classes", e);
        }
    }

    public void loadListeners() throws Exception {
        List<File> classes = ClassFileUtil.getClassesInClassPath();
        for (File classFile : classes) {
            Class<?> clazz = Class.forName(ClassFileUtil.getClassName(classFile));
            if (clazz.isAnnotationPresent(EventListener.class)) {
                if (validateListener(clazz)) {
                    registerListener(clazz);
                } else {
                    LOGGER.error("{} failed validation", clazz.getName());
                }
            }
        }
    }

    private void registerListener(Class<?> clazz) {
        try {
            eventListeners.add(getZeroArgConstructor(clazz).newInstance());
            LOGGER.info("Registered listener {}", clazz.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
    }

    public boolean validateListener(Class<?> clazz) {
        return eventListenerValidator.validate(clazz);
    }

    public static Constructor<?> getZeroArgConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        throw new RuntimeException("No zero arg constructor found for " + clazz.getName());
    }
}
