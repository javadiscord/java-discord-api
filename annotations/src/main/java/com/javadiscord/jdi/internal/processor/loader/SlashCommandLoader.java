package com.javadiscord.jdi.internal.processor.loader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.internal.exceptions.ValidationException;
import com.javadiscord.jdi.internal.processor.ClassFileUtil;
import com.javadiscord.jdi.internal.processor.SlashCommandClassMethod;
import com.javadiscord.jdi.internal.processor.validator.SlashCommandValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SlashCommandLoader {
    private static final Logger LOGGER = LogManager.getLogger(SlashCommandLoader.class);
    private final Map<String, SlashCommandClassMethod> interactionListeners;
    private final SlashCommandValidator validator = new SlashCommandValidator();

    public SlashCommandLoader(Map<String, SlashCommandClassMethod> interactionListeners) {
        this.interactionListeners = interactionListeners;
        loadInteractionListeners();
    }

    private void loadInteractionListeners() {
        List<File> classes = ClassFileUtil.getClassesInClassPath();
        for (File classFile : classes) {
            try {
                Class<?> clazz = Class.forName(ClassFileUtil.getClassName(classFile));

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.getAnnotation(SlashCommand.class) != null) {
                        if (validator.validate(method)) {
                            registerListener(
                                clazz, method, method.getAnnotation(SlashCommand.class).name(),
                                createInstance(clazz)
                            );
                        } else {
                            throw new ValidationException(method.getName() + " failed validation");
                        }
                    }
                }
            } catch (Exception ignore) {
                /* Ignore */
            }
        }
    }

    private Object createInstance(Class<?> clazz) {
        Object instance = null;
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            if (constructor.getParameterCount() > 0) {
                instance = constructor.newInstance(getConstructorParameters(constructor).toArray());
            } else {
                instance = constructor.newInstance();
            }
            injectComponents(instance);
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
        return instance;
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

    private void registerListener(Class<?> clazz, Method method, String name, Object instance) {
        try {
            if (interactionListeners.containsKey(name)) {
                LOGGER.error(
                    "Failed to register command {} from {} as that name already exists in {}",
                    name,
                    clazz.getName(),
                    interactionListeners.get(name).getClass().getName()
                );
                return;
            }
            interactionListeners.put(name, new SlashCommandClassMethod(clazz, method, instance));
            LOGGER.info("Found slash command handler {}", clazz.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
    }

    public SlashCommandClassMethod getSlashCommandClassMethod(String name) {
        return interactionListeners.get(name);
    }

    public void injectComponents(Object object) {
        ComponentLoader.injectComponents(object);
    }

}
