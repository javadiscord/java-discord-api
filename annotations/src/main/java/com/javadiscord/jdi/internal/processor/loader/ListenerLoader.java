package com.javadiscord.jdi.internal.processor.loader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.internal.processor.ClassFileUtil;
import com.javadiscord.jdi.internal.processor.validator.EventListenerValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerLoader {
    private static final Logger LOGGER = LogManager.getLogger(ListenerLoader.class);
    private final EventListenerValidator eventListenerValidator = new EventListenerValidator();
    private final List<Object> eventListeners;
    private final ComponentLoader componentLoader;

    public ListenerLoader(List<Object> eventListeners, ComponentLoader componentLoader) {
        this.eventListeners = eventListeners;
        this.componentLoader = componentLoader;
        try {
            loadListeners();
        } catch (Exception e) {
            LOGGER.error("An error occurred while loading annotated classes", e);
        }
    }

    public void loadListeners() {
        List<File> classes = ClassFileUtil.getClassesInClassPath();
        for (File classFile : classes) {
            try {
                Class<?> clazz = Class.forName(ClassFileUtil.getClassName(classFile));
                if (clazz.isAnnotationPresent(EventListener.class)) {
                    if (validateListener(clazz)) {
                        registerListener(clazz);
                    } else {
                        LOGGER.error("{} failed validation", clazz.getName());
                    }
                }
            } catch (Exception ignore) {
                /* Ignore */
            }
        }
    }

    private void registerListener(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            Object instance;
            if (constructor.getParameterCount() > 0) {
                instance = constructor.newInstance(getConstructorParameters(constructor).toArray());
            } else {
                instance = constructor.newInstance();
            }
            ComponentLoader.injectComponents(instance);
            eventListeners.add(instance);
            LOGGER.info("Registered listener {}", clazz.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
    }

    private List<Object> getConstructorParameters(Constructor<?> constructor) {
        List<Object> constructorParameters = new ArrayList<>();
        for (Parameter parameter : constructor.getParameters()) {
            if (ComponentLoader.COMPONENTS.containsKey(parameter.getType())) {
                constructorParameters.add(ComponentLoader.COMPONENTS.get(parameter.getType()));
            } else {
                constructorParameters.add(null);
                LOGGER.warn("No component found for {}", parameter.getType());
            }
        }
        return constructorParameters;
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
        return null;
    }
}
