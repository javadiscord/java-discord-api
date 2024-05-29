package com.javadiscord.jdi.core.interaction;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.GatewayEventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.guild.Interaction;
import com.javadiscord.jdi.internal.ReflectiveLoader;
import com.javadiscord.jdi.internal.ReflectiveSlashCommandClassMethod;
import com.javadiscord.jdi.internal.ReflectiveSlashCommandLoader;

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
            ReflectiveSlashCommandLoader reflectiveSlashCommandLoader =
                ReflectiveLoader.proxy(slashCommandLoader, ReflectiveSlashCommandLoader.class);

            ReflectiveSlashCommandClassMethod reflectiveSlashCommandClassMethod =
                ReflectiveLoader.proxy(
                    reflectiveSlashCommandLoader.getSlashCommandClassMethod(command),
                    ReflectiveSlashCommandClassMethod.class
                );

            Class<?> handler = reflectiveSlashCommandClassMethod.clazz();
            Method method = reflectiveSlashCommandClassMethod.method();

            List<Object> paramOrder = getOrderOfParameters(method, interaction);

            if (validateParameterCount(method, paramOrder)) {
                invokeHandler(handler, method, paramOrder);
            } else {
                throw new RuntimeException(
                    "Bound " + paramOrder.size() + " parameters but expected "
                        + method.getParameterCount()
                );
            }

        } catch (Exception e) {
            LOGGER.error("Failed to invoke handler for /{}", command, e);
        }
    }

    private List<Object> getOrderOfParameters(Method method, Interaction interaction) {
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

        return paramOrder;
    }

    private boolean validateParameterCount(Method method, List<Object> paramOrder) {
        return paramOrder.size() == method.getParameterCount();
    }

    private void invokeHandler(
        Class<?> handler,
        Method method,
        List<Object> paramOrder
    ) throws Exception {
        if (cachedInstances.containsKey(handler.getName())) {
            method.invoke(cachedInstances.get(handler.getName()), paramOrder.toArray());
        } else {
            Object handlerInstance = handler.getDeclaredConstructor().newInstance();
            cachedInstances.put(handler.getName(), handlerInstance);
            injectComponents(handlerInstance);
            method.invoke(handlerInstance, paramOrder.toArray());
        }
    }

    private void injectComponents(Object object) {
        ReflectiveSlashCommandLoader reflectiveSlashCommandLoader =
            ReflectiveLoader.proxy(slashCommandLoader, ReflectiveSlashCommandLoader.class);

        reflectiveSlashCommandLoader.injectComponents(object);
    }
}
