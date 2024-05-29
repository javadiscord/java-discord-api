package com.javadiscord.jdi.core.processor.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.javadiscord.jdi.core.annotations.SlashCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SlashCommandValidator {
    private static final Logger LOGGER = LogManager.getLogger(SlashCommandValidator.class);
    private static final Map<Class<? extends Annotation>, String[]> EXPECTED_PARAM_TYPES_MAP =
        new HashMap<>();

    static {
        EXPECTED_PARAM_TYPES_MAP.put(
            SlashCommand.class,
            new String[] {
                "com.javadiscord.jdi.core.models.guild.Interaction",
                "com.javadiscord.jdi.core.Discord",
                "com.javadiscord.jdi.core.Guild",
                "com.javadiscord.jdi.core.interaction.SlashCommandEvent"
            }
        );
    }

    public boolean validate(Method method) {
        for (Map.Entry<Class<? extends Annotation>, String[]> entry : EXPECTED_PARAM_TYPES_MAP
            .entrySet()) {
            Class<? extends Annotation> annotationClass = entry.getKey();
            if (
                method.isAnnotationPresent(annotationClass)
                    && !validateMethodParameters(method, entry.getValue())
            ) {
                return false;
            }
        }
        return true;
    }

    private boolean validateMethodParameters(Method method, String[] expectedParamTypes) {
        if (method.getParameterCount() > 0) {
            return checkParameterTypes(method, expectedParamTypes);
        }
        return true;
    }

    private boolean checkParameterTypes(Method method, String[] expectedParamTypes) {
        Class<?>[] paramTypes = method.getParameterTypes();
        for (Class<?> type : paramTypes) {
            if (!isExpectedType(type, expectedParamTypes)) {
                LOGGER.error("Unexpected parameter found: {}", type.getName());
                return false;
            }
        }
        return true;
    }

    private boolean isExpectedType(Class<?> type, String[] expectedParamTypes) {
        for (String expectedType : expectedParamTypes) {
            if (type.getName().equals(expectedType)) {
                return true;
            }
        }
        return false;
    }
}
