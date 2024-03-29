package com.javadiscord.jdi.internal.gateway.handlers.events;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.GatewayOperationHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.ReadyEventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.ready.ReadyEventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.ResumeEventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.resume.ResumeEventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread.*;

import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.UserDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user.UserUpdateHandler;
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
        EVENT_HANDLERS.put(EventType.GUILD_CREATE, new GuildCreateHandler());
        EVENT_HANDLERS.put(EventType.GUILD_DELETE, new GuildDeleteHandler());
        EVENT_HANDLERS.put(EventType.GUILD_UPDATE, new GuildUpdateEventHandler());

        ChannelDecoder channelDecoder = new ChannelDecoder();
        EVENT_DECODERS.put(EventType.CHANNEL_CREATE, channelDecoder);
        EVENT_DECODERS.put(EventType.CHANNEL_UPDATE, channelDecoder);
        EVENT_DECODERS.put(EventType.CHANNEL_DELETE, channelDecoder);
        EVENT_HANDLERS.put(EventType.CHANNEL_CREATE, new ChannelCreateHandler());
        EVENT_HANDLERS.put(EventType.CHANNEL_DELETE, new ChannelDeleteHandler());
        EVENT_HANDLERS.put(EventType.CHANNEL_UPDATE, new ChannelUpdateHandler());

        MessageDecoder messageDecoder = new MessageDecoder();
        EVENT_DECODERS.put(EventType.MESSAGE_CREATE, messageDecoder);
        EVENT_DECODERS.put(EventType.MESSAGE_DELETE, messageDecoder);
        EVENT_DECODERS.put(EventType.MESSAGE_UPDATE, messageDecoder);
        EVENT_HANDLERS.put(EventType.MESSAGE_CREATE, new MessageCreateHandler());
        EVENT_HANDLERS.put(EventType.MESSAGE_DELETE, new MessageDeleteHandler());
        EVENT_HANDLERS.put(EventType.MESSAGE_UPDATE, new MessageUpdateHandler());

        MessageReactionDecoder messageReactionDecoder = new MessageReactionDecoder();
        EVENT_DECODERS.put(EventType.MESSAGE_REACTION_ADD, messageReactionDecoder);
        EVENT_DECODERS.put(EventType.MESSAGE_REACTION_REMOVE, messageReactionDecoder);
        EVENT_DECODERS.put(EventType.MESSAGE_REACTION_REMOVE_EMOJI, messageReactionDecoder);

        EVENT_HANDLERS.put(EventType.MESSAGE_REACTION_ADD, new ReactionAddHandler());

        ReactionRemoveHandler reactionRemoveHandler = new ReactionRemoveHandler();
        EVENT_HANDLERS.put(EventType.MESSAGE_REACTION_REMOVE, reactionRemoveHandler);
        EVENT_HANDLERS.put(EventType.MESSAGE_REACTION_REMOVE_EMOJI, reactionRemoveHandler);

        EVENT_DECODERS.put(EventType.TYPING_START, new TypingStartDecoder());
        EVENT_HANDLERS.put(EventType.TYPING_START, new TypingStartHandler());

        EVENT_DECODERS.put(EventType.CHANNEL_PINS_UPDATE, new ChannelPinUpdateDecoder());
        EVENT_HANDLERS.put(EventType.CHANNEL_PINS_UPDATE, new ChannelPinUpdateHandler());

        EVENT_DECODERS.put(EventType.GUILD_ROLE_CREATE, new GuildRoleDecoder());
        EVENT_HANDLERS.put(EventType.GUILD_ROLE_CREATE, new GuildRoleCreateHandler());

        GuildRoleDecoder guildRoleDecoder = new GuildRoleDecoder();
        EVENT_DECODERS.put(EventType.GUILD_ROLE_CREATE, guildRoleDecoder);
        EVENT_DECODERS.put(EventType.GUILD_ROLE_UPDATE, guildRoleDecoder);
        EVENT_DECODERS.put(EventType.GUILD_ROLE_DELETE, guildRoleDecoder);
        EVENT_HANDLERS.put(EventType.GUILD_ROLE_UPDATE, new GuildRoleUpdateHandler());
        EVENT_HANDLERS.put(EventType.GUILD_ROLE_DELETE, new GuildDeleteHandler());

        ThreadDecoder threadDecoder = new ThreadDecoder();
        EVENT_DECODERS.put(EventType.THREAD_CREATE, threadDecoder);
        EVENT_DECODERS.put(EventType.THREAD_UPDATE, threadDecoder);
        EVENT_DECODERS.put(EventType.THREAD_DELETE, threadDecoder);
        EVENT_HANDLERS.put(EventType.THREAD_CREATE, new ThreadCreateHandler());
        EVENT_HANDLERS.put(EventType.THREAD_UPDATE, new ThreadUpdateHandler());
        EVENT_HANDLERS.put(EventType.THREAD_DELETE, new ThreadDeleteHandler());

        GuildMemberDecoder guildMemberDecoder = new GuildMemberDecoder();
        EVENT_DECODERS.put(EventType.GUILD_MEMBER_UPDATE, guildMemberDecoder);
        EVENT_DECODERS.put(EventType.GUILD_MEMBER_REMOVE, guildMemberDecoder);
        EVENT_DECODERS.put(EventType.GUILD_MEMBER_ADD, guildMemberDecoder);
        EVENT_HANDLERS.put(EventType.GUILD_MEMBER_UPDATE, new GuildMemberUpdateHandler());
        EVENT_HANDLERS.put(EventType.GUILD_MEMBER_REMOVE, new GuildMemberRemoveHandler());
        EVENT_HANDLERS.put(EventType.GUILD_MEMBER_ADD, new GuildMemberAddHandler());

        GuildInviteDecoder inviteDecoder = new GuildInviteDecoder();
        EVENT_DECODERS.put(EventType.INVITE_CREATE, inviteDecoder);
        EVENT_DECODERS.put(EventType.INVITE_DELETE, inviteDecoder);
        EVENT_HANDLERS.put(EventType.INVITE_CREATE, new GuildInviteCreateHandler());
        EVENT_HANDLERS.put(EventType.INVITE_DELETE, new GuildInviteDeleteHandler());

        EVENT_DECODERS.put(EventType.USER_UPDATE, new UserDecoder());
        EVENT_HANDLERS.put(EventType.USER_UPDATE, new UserUpdateHandler());

        EVENT_DECODERS.put(EventType.THREAD_LIST_SYNC, new ThreadListSyncDecoder());
        EVENT_HANDLERS.put(EventType.THREAD_LIST_SYNC, new ThreadListSyncHandler());

        EVENT_DECODERS.put(EventType.THREAD_MEMBER_UPDATE, new ThreadMemberDecoder());
        EVENT_HANDLERS.put(EventType.THREAD_MEMBER_UPDATE, new ThreadMemberHandler());

        EVENT_DECODERS.put(EventType.THREAD_MEMBERS_UPDATE, new ThreadMemberDecoder());
        EVENT_HANDLERS.put(EventType.THREAD_MEMBERS_UPDATE, new ThreadMemberUpdateHandler());

        GuildBanDecoder guildBanDecoder = new GuildBanDecoder();
        EVENT_DECODERS.put(EventType.GUILD_BAN_ADD, guildBanDecoder);
        EVENT_HANDLERS.put(EventType.GUILD_BAN_ADD, new GuildBanHandler());

        EVENT_DECODERS.put(EventType.GUILD_BAN_REMOVE, guildBanDecoder);
        EVENT_HANDLERS.put(EventType.GUILD_BAN_REMOVE, new GuildBanRemoveHandler());
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
