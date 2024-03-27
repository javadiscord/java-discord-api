package com.javadiscord.gateway.handlers.events;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.GatewayOperationHandler;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.gateway.handlers.events.codec.channel.ChannelDecoder;
import com.javadiscord.gateway.handlers.events.codec.channel.ChannelCreateHandler;
import com.javadiscord.gateway.handlers.events.codec.channel.ChannelDeleteHandler;
import com.javadiscord.gateway.handlers.events.codec.channel.ChannelUpdateHandler;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildDecoder;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildCreateEventHandler;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildDeleteEventHandler;
import com.javadiscord.gateway.handlers.events.codec.guild.GuildUpdateEventHandler;
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

        GuildDecoder guildDecoder = new GuildDecoder();
        EVENT_DECODERS.put(EventType.GUILD_CREATE, guildDecoder);
        EVENT_DECODERS.put(EventType.GUILD_DELETE, guildDecoder);
        EVENT_DECODERS.put(EventType.GUILD_UPDATE, guildDecoder);
        EVENT_HANDLERS.put(EventType.GUILD_CREATE, new GuildCreateEventHandler());
        EVENT_HANDLERS.put(EventType.GUILD_DELETE, new GuildDeleteEventHandler());
        EVENT_HANDLERS.put(EventType.GUILD_UPDATE, new GuildUpdateEventHandler());

        ChannelDecoder channelDecoder = new ChannelDecoder();
        EVENT_DECODERS.put(EventType.CHANNEL_CREATE, channelDecoder);
        EVENT_DECODERS.put(EventType.CHANNEL_UPDATE, channelDecoder);
        EVENT_DECODERS.put(EventType.CHANNEL_DELETE, channelDecoder);
        EVENT_HANDLERS.put(EventType.CHANNEL_CREATE, new ChannelCreateHandler());
        EVENT_HANDLERS.put(EventType.CHANNEL_DELETE, new ChannelDeleteHandler());
        EVENT_HANDLERS.put(EventType.CHANNEL_UPDATE, new ChannelUpdateHandler());

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

        EventDecoder<Object> eventDecoder = (EventDecoder<Object>) EVENT_DECODERS.get(eventType);
        EventHandler<Object> eventHandler = (EventHandler<Object>) EVENT_HANDLERS.get(eventType);
        Object event = eventDecoder.decode(gatewayEvent);
        eventHandler.handle(event, connectionMediator, discord);
    }
}
