package com.javadiscord.jdi.core.interaction;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.GatewayEventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.guild.Interaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InteractionEventHandler implements EventListener {
    private static final Logger LOGGER = LogManager.getLogger(InteractionEventHandler.class);
    private final Object slashCommandLoader;
    private final Discord discord;

    private final Map<String, Object> cachedInstances = new HashMap<>();

    public InteractionEventHandler(Object slashCommandLoader, Discord discord) {
        this.slashCommandLoader = slashCommandLoader;
        this.discord = discord;
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

            List<Object> paramOrder = new ArrayList<>();
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.getParameterizedType() == interaction.getClass()) {
                    paramOrder.add(interaction);
                } else if (parameter.getParameterizedType() == Discord.class) {
                    paramOrder.add(discord);
                } else if (parameter.getParameterizedType() == Guild.class) {
                    paramOrder.add(GatewayEventListener.getGuild(discord, interaction.guild()));
                } else if (parameter.getParameterizedType() == SlashCommandEvent.class) {
                    paramOrder.add(new SlashCommandEvent(interaction, discord));
                }
            }

            if (paramOrder.size() != method.getParameterCount()) {
                throw new RuntimeException(
                    "Bound "
                        + paramOrder.size()
                        + " parameters but expected "
                        + method.getParameterCount()
                );
            }

            if (cachedInstances.containsKey(handler.getName())) {
                method.invoke(cachedInstances.get(handler.getName()), paramOrder.toArray());
            } else {
                Object handlerInstance = handler.getDeclaredConstructor().newInstance();
                cachedInstances.put(handler.getName(), handlerInstance);
                injectComponents(handlerInstance);
                method.invoke(handlerInstance, paramOrder.toArray());
            }

        } catch (Exception e) {
            LOGGER.error("Failed to invoke handler for /{}", command, e);
        }
    }

    private void injectComponents(Object object) throws Exception {
        Class<?> slashCommandLoaderClass = slashCommandLoader.getClass();

        Method injectComponentsMethod =
            slashCommandLoaderClass.getMethod("injectComponents", Object.class);

        injectComponentsMethod.invoke(slashCommandLoader, object);
    }

}
