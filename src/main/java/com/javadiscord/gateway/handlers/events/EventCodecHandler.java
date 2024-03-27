package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.GatewayOperationHandler;
import com.javadiscord.gateway.handlers.events.codec.Event;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildCreateEventDecoder;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildCreateEventHandler;
import com.javadiscord.gateway.handlers.events.codec.ready.ReadyEventDecoder;
import com.javadiscord.gateway.handlers.events.codec.ready.ReadyEventHandler;
import com.javadiscord.gateway.handlers.events.codec.resume.ResumeEventDecoder;
import com.javadiscord.gateway.handlers.events.codec.resume.ResumeEventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EventCodecHandler implements GatewayOperationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<EventType, EventDecoder<?>> EVENT_DECODERS = new HashMap<>();
    private static final Map<EventType, EventHandler<?>> EVENT_HANDLERS = new HashMap<>();

    static {
        EVENT_DECODERS.put(EventType.READY, new ReadyEventDecoder());
        EVENT_HANDLERS.put(EventType.READY, new ReadyEventHandler());

        EVENT_DECODERS.put(EventType.RESUMED, new ResumeEventDecoder());
        EVENT_HANDLERS.put(EventType.RESUMED, new ResumeEventHandler());

        EVENT_DECODERS.put(EventType.GUILD_CREATE, new GuildCreateEventDecoder());
        EVENT_HANDLERS.put(EventType.GUILD_CREATE, new GuildCreateEventHandler());
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void handle(
            GatewayEvent gatewayEvent, ConnectionMediator connectionMediator, Discord discord) {
        LOGGER.info("Received event {}, {}", gatewayEvent.eventName(), gatewayEvent.data());

        if (!EventType.nameExists(gatewayEvent.eventName())) {
            LOGGER.warn("Unknown event received {}", gatewayEvent.eventName());
            return;
        }

        EventType eventType = EventType.valueOf(gatewayEvent.eventName());

        if (!EVENT_DECODERS.containsKey(eventType)) {
            LOGGER.warn("No decoder found for {}", gatewayEvent.eventName());
            return;
        }

        if (!EVENT_HANDLERS.containsKey(eventType)) {
            LOGGER.warn("No handler found for {}", gatewayEvent.eventName());
            return;
        }

        EventDecoder<Event> eventDecoder = (EventDecoder<Event>) EVENT_DECODERS.get(eventType);
        EventHandler<Event> eventHandler = (EventHandler<Event>) EVENT_HANDLERS.get(eventType);
        Event event = eventDecoder.decode(gatewayEvent);
        eventHandler.handle(event, connectionMediator, discord);
    }
}
