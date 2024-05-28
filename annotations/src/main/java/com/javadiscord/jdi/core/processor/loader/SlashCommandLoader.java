package com.javadiscord.jdi.core.processor.loader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.processor.ClassFileUtil;
import com.javadiscord.jdi.core.processor.SlashCommandClassMethod;
import com.javadiscord.jdi.core.processor.validator.SlashCommandValidator;

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
                        if (validator.validate(method) && hasZeroArgsConstructor(clazz)) {
                            registerListener(
                                clazz, method, method.getAnnotation(SlashCommand.class).name()
                            );
                        } else {
                            LOGGER.error("{} failed validation", method.getName());
                        }
                    }
                }
            } catch (Exception | Error ignore) {
                /* Ignore */
            }
        }
    }

    private void registerListener(Class<?> clazz, Method method, String name) {
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
            interactionListeners.put(name, new SlashCommandClassMethod(clazz, method));
            LOGGER.info("Found slash command handler {}", clazz.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
    }

    public SlashCommandClassMethod getSlashCommandClassMethod(String name) {
        return interactionListeners.get(name);
    }

    private boolean hasZeroArgsConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        LOGGER.error("{} does not have a 0 arg constructor", clazz.getName());
        return false;
    }

    public void injectComponents(Object object) {
        ComponentLoader.injectComponents(object);
    }

}
