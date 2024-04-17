package com.javadiscord.jdi.core.processor;

import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.processor.validator.EventListenerValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventListenerFinder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final EventListenerValidator EVENT_LISTENER_VALIDATOR =
            new EventListenerValidator();
    private static final List<Object> EVENT_LISTENERS = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<File> classes = ClassLoader.getClassesInClassPath();

        for (File classFile : classes) {
            Class<?> clazz = Class.forName(ClassLoader.getClassName(classFile));
            if (clazz.isAnnotationPresent(EventListener.class)) {
                if (EVENT_LISTENER_VALIDATOR.validate(clazz)) {
                    try {
                        EVENT_LISTENERS.add(clazz.newInstance());
                    } catch (Exception e) {
                        LOGGER.error("Failed to create {} instance", clazz.getName(), e);
                    }
                }
            }
        }
    }
}
