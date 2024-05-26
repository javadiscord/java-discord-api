package com.javadiscord.jdi.core.interaction;

import java.lang.reflect.Method;

import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.guild.Interaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InteractionEventHandler implements EventListener {
    private static final Logger LOGGER = LogManager.getLogger(InteractionEventHandler.class);
    private final Object slashCommandLoader;

    public InteractionEventHandler(Object slashCommandLoader) {
        this.slashCommandLoader = slashCommandLoader;
    }

    @Override
    public void onInteractionCreate(Interaction interaction, Guild guild) {
        String command = interaction.data().name();

        try {
            Class<?> slashCommandLoaderClass = slashCommandLoader.getClass();

            Method getSlashCommandClassMethod =
                slashCommandLoaderClass.getMethod("getSlashCommandClassMethod", String.class);

            Object commandClassMethodInstance =
                getSlashCommandClassMethod.invoke(slashCommandLoader, command);

            if (commandClassMethodInstance == null) {
                LOGGER.warn("No handler found for /{} command", command);
                return;
            }

            Class<?> handler =
                (Class<?>) commandClassMethodInstance.getClass().getMethod("clazz")
                    .invoke(commandClassMethodInstance);

            Method method =
                (Method) commandClassMethodInstance.getClass().getMethod("method")
                    .invoke(commandClassMethodInstance);

            Object handlerInstance = handler.getDeclaredConstructor().newInstance();
            method.invoke(handlerInstance);

        } catch (Exception e) {
            LOGGER.error("Failed to invoke handler for /{}", command, e);
        }
    }

}
