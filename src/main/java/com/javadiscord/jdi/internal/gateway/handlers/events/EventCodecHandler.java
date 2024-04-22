package com.javadiscord.jdi.internal.gateway.handlers.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.annotations.ChannelCreate;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.GatewayOperationHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.ReadyEventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.ResumeEventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders.UserDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod.AutoModerationRuleCreateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod.AutoModerationRuleDeleteHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod.AutoModerationRuleUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement.EntitlementCreateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement.EntitlementDeleteHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.entitlement.EntitlementUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.channel.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.event.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.integration.IntegrationUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage.StageCreateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage.StageDeleteHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.stage.StageUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.sticker.StickerUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.thread.*;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user.MemberChunkHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.user.UserUpdateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.interaction.InteractionCreateHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.ready.ReadyEventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.resume.ResumeEventHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.voice.VoiceServerHandler;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.voice.VoiceStateHandler;
import com.javadiscord.jdi.internal.models.channel.Channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventCodecHandler implements GatewayOperationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<EventType, EventDecoder<?>> EVENT_DECODERS = new HashMap<>();
    private static final Map<EventType, EventHandler<?>> EVENT_HANDLERS = new HashMap<>();
    private static final Map<EventType, Class<? extends Annotation>> EVENT_TYPE_ANNOTATIONS = new HashMap<>();

    static {
        /* Event Listeners */
        EVENT_TYPE_ANNOTATIONS.put(EventType.CHANNEL_CREATE, ChannelCreate.class);

        /* Codec */
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
        EVENT_HANDLERS.put(EventType.GUILD_ROLE_DELETE, new GuildRoleDeleteHandler());

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

        EVENT_DECODERS.put(EventType.WEBHOOKS_UPDATE, new WebhookDecoder());
        EVENT_HANDLERS.put(EventType.WEBHOOKS_UPDATE, new WebHookHandler());

        EVENT_DECODERS.put(EventType.VOICE_SERVER_UPDATE, new VoiceServerDecoder());
        EVENT_HANDLERS.put(EventType.VOICE_SERVER_UPDATE, new VoiceServerHandler());

        EVENT_DECODERS.put(EventType.VOICE_STATE_UPDATE, new VoiceStateDecoder());
        EVENT_HANDLERS.put(EventType.VOICE_STATE_UPDATE, new VoiceStateHandler());

        EVENT_DECODERS.put(EventType.MESSAGE_DELETE_BULK, new MessageBulkDeleteDecoder());
        EVENT_HANDLERS.put(EventType.MESSAGE_DELETE_BULK, new MessageBulkDeleteHandler());

        EVENT_DECODERS.put(
            EventType.MESSAGE_REACTION_REMOVE_ALL, new MessageReactionsRemovedDecoder()
        );
        EVENT_HANDLERS.put(
            EventType.MESSAGE_REACTION_REMOVE_ALL, new MessageReactionsRemovedHandler()
        );

        StageDecoder stageDecoder = new StageDecoder();
        EVENT_DECODERS.put(EventType.STAGE_INSTANCE_CREATE, stageDecoder);
        EVENT_DECODERS.put(EventType.STAGE_INSTANCE_UPDATE, stageDecoder);
        EVENT_DECODERS.put(EventType.STAGE_INSTANCE_DELETE, stageDecoder);
        EVENT_HANDLERS.put(EventType.STAGE_INSTANCE_CREATE, new StageCreateHandler());
        EVENT_HANDLERS.put(EventType.STAGE_INSTANCE_UPDATE, new StageUpdateHandler());
        EVENT_HANDLERS.put(EventType.STAGE_INSTANCE_DELETE, new StageDeleteHandler());

        EVENT_DECODERS.put(EventType.INTERACTION_CREATE, new InteractionCreateDecoder());
        EVENT_HANDLERS.put(EventType.INTERACTION_CREATE, new InteractionCreateHandler());

        AutoModerationDecoder autoModerationDecoder = new AutoModerationDecoder();
        EVENT_DECODERS.put(EventType.AUTO_MODERATION_RULE_CREATE, autoModerationDecoder);
        EVENT_DECODERS.put(EventType.AUTO_MODERATION_RULE_UPDATE, autoModerationDecoder);
        EVENT_DECODERS.put(EventType.AUTO_MODERATION_RULE_DELETE, autoModerationDecoder);
        EVENT_DECODERS.put(EventType.AUTO_MODERATION_ACTION_EXECUTION, autoModerationDecoder);
        EVENT_HANDLERS.put(
            EventType.AUTO_MODERATION_RULE_CREATE, new AutoModerationRuleCreateHandler()
        );
        EVENT_HANDLERS.put(
            EventType.AUTO_MODERATION_RULE_UPDATE, new AutoModerationRuleUpdateHandler()
        );
        EVENT_HANDLERS.put(
            EventType.AUTO_MODERATION_RULE_DELETE, new AutoModerationRuleDeleteHandler()
        );
        EVENT_HANDLERS.put(
            EventType.AUTO_MODERATION_ACTION_EXECUTION, new AutoModerationRuleDeleteHandler()
        );

        EntitlementDecoder entitlementDecoder = new EntitlementDecoder();
        EVENT_DECODERS.put(EventType.ENTITLEMENT_CREATE, entitlementDecoder);
        EVENT_DECODERS.put(EventType.ENTITLEMENT_DELETE, entitlementDecoder);
        EVENT_DECODERS.put(EventType.ENTITLEMENT_UPDATE, entitlementDecoder);

        EVENT_HANDLERS.put(EventType.ENTITLEMENT_CREATE, new EntitlementCreateHandler());
        EVENT_HANDLERS.put(EventType.ENTITLEMENT_DELETE, new EntitlementUpdateHandler());
        EVENT_HANDLERS.put(EventType.ENTITLEMENT_UPDATE, new EntitlementDeleteHandler());

        ScheduledEventDecoder scheduledEventDecoder = new ScheduledEventDecoder();
        EVENT_DECODERS.put(EventType.GUILD_SCHEDULED_EVENT_CREATE, scheduledEventDecoder);
        EVENT_DECODERS.put(EventType.GUILD_SCHEDULED_EVENT_UPDATE, scheduledEventDecoder);
        EVENT_DECODERS.put(EventType.GUILD_SCHEDULED_EVENT_DELETE, scheduledEventDecoder);

        EVENT_HANDLERS.put(
            EventType.GUILD_SCHEDULED_EVENT_CREATE, new ScheduledEventCreateHandler()
        );
        EVENT_HANDLERS.put(
            EventType.GUILD_SCHEDULED_EVENT_UPDATE, new ScheduledEventUpdateHandler()
        );
        EVENT_HANDLERS.put(
            EventType.GUILD_SCHEDULED_EVENT_DELETE, new ScheduledEventDeleteHandler()
        );

        EventUserDecoder eventUserDecoder = new EventUserDecoder();
        EVENT_DECODERS.put(EventType.GUILD_SCHEDULED_EVENT_USER_ADD, eventUserDecoder);
        EVENT_DECODERS.put(EventType.GUILD_SCHEDULED_EVENT_USER_REMOVE, eventUserDecoder);
        EVENT_HANDLERS.put(
            EventType.GUILD_SCHEDULED_EVENT_USER_ADD, new ScheduledEventUserAddHandler()
        );
        EVENT_HANDLERS.put(
            EventType.GUILD_SCHEDULED_EVENT_USER_REMOVE, new ScheduledEventUserRemoveHandler()
        );

        EVENT_DECODERS.put(EventType.GUILD_STICKERS_UPDATE, new StickerUpdateDecoder());
        EVENT_HANDLERS.put(EventType.GUILD_STICKERS_UPDATE, new StickerUpdateHandler());

        EVENT_DECODERS.put(EventType.GUILD_INTEGRATIONS_UPDATE, new IntegrationUpdateDecoder());
        EVENT_HANDLERS.put(EventType.GUILD_INTEGRATIONS_UPDATE, new IntegrationUpdateHandler());

        EVENT_DECODERS.put(EventType.GUILD_MEMBERS_CHUNK, new MemberChunkDecoder());
        EVENT_HANDLERS.put(EventType.GUILD_MEMBERS_CHUNK, new MemberChunkHandler());
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public void handle(
        GatewayEvent gatewayEvent, ConnectionMediator connectionMediator, Discord discord
    ) {
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

        if (EVENT_TYPE_ANNOTATIONS.containsKey(eventType)) {
            invokeListeners(discord, eventType, event);
        }
    }

    private static void invokeListeners(Discord discord, EventType eventType, Object event) {
        discord.getEventListeners().forEach(listener -> {
            Method[] methods = listener.getClass().getMethods();
            List<Object> paramOrder = new ArrayList<>();
            for (Method method : methods) {
                if (method.isAnnotationPresent(EVENT_TYPE_ANNOTATIONS.get(eventType))) {
                    Parameter[] parameters = method.getParameters();
                    for (Parameter parameter : parameters) {
                        if (parameter.getParameterizedType() == Channel.class) {
                            paramOrder.add(event);
                        } else if (parameter.getParameterizedType() == Discord.class) {
                            paramOrder.add(discord);
                        }
                    }
                    try {
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
