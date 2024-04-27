package com.javadiscord.jdi.core;

import com.javadiscord.jdi.internal.gateway.handlers.events.EventCodecHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.EventType;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.GatewayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class GatewayEventListener implements GatewayObserver  {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Discord discord;

    public GatewayEventListener(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void receive(EventType eventType, Object event) {
        discord.getEventListeners().forEach(listener -> {
            Method[] methods = listener.getClass().getMethods();
            List<Object> paramOrder = new ArrayList<>();
            for (Method method : methods) {
                if (method.isAnnotationPresent(EventCodecHandler.EVENT_TYPE_ANNOTATIONS.get(eventType))) {
                    Parameter[] parameters = method.getParameters();
                    for (Parameter parameter : parameters) {
                        if (parameter.getParameterizedType() == event.getClass()) {
                            paramOrder.add(event);
                        } else if (parameter.getParameterizedType() == Discord.class) {
                            paramOrder.add(this);
                        }
                    }
                    try {
                        if (paramOrder.size() != method.getParameterCount()) {
                            throw new RuntimeException(
                                    "Bound " + paramOrder.size() + " parameters but expected " + method.getParameterCount()
                            );
                        }
                        LOGGER.trace("Invoking method {} with params {}", method.getName(), paramOrder);
                        method.invoke(listener, paramOrder.toArray());
                    } catch (Exception e) {
                        LOGGER.error("Failed to invoke {}", method.getName(), e);
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
