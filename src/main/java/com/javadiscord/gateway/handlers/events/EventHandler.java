package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.MessageHandler;
import com.javadiscord.gateway.handlers.events.ready.ReadyEventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EventHandler implements MessageHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Event, EventListener> EVENT_LISTENER = new HashMap<>();

    static {
        EVENT_LISTENER.put(Event.READY, new ReadyEventListener());
        EVENT_LISTENER.put(Event.RESUMED, new ResumedEventListener());
    }

    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.info("Received event {}, {}", event.eventName(), event.data());

        if (Event.nameExists(event.eventName())
                && EVENT_LISTENER.containsKey(Event.valueOf(event.eventName()))) {
            EVENT_LISTENER
                    .get(Event.valueOf(event.eventName()))
                    .onEvent(event, connectionMediator, discord);
        } else {
            LOGGER.warn("Unknown event received {}", event.eventName());
        }
    }
}
