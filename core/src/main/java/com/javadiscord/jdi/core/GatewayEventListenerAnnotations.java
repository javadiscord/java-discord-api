package com.javadiscord.jdi.core;

import com.javadiscord.jdi.internal.gateway.handlers.events.EventType;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.GatewayObserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GatewayEventListenerAnnotations implements GatewayObserver {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final Map<EventType, String> EVENT_TYPE_ANNOTATIONS = new HashMap<>();

    static {
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_CREATE, "com.javadiscord.jdi.core.annotations.GuildCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_DELETE, "com.javadiscord.jdi.core.annotations.GuildDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_UPDATE, "com.javadiscord.jdi.core.annotations.GuildUpdateEvent");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.CHANNEL_CREATE, "com.javadiscord.jdi.core.annotations.ChannelCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_CREATE, "com.javadiscord.jdi.core.annotations.MessageCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_DELETE, "com.javadiscord.jdi.core.annotations.MessageDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_UPDATE, "com.javadiscord.jdi.core.annotations.MessageUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_REACTION_REMOVE,
                "com.javadiscord.jdi.core.annotations.ReactionRemove");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_REACTION_REMOVE_EMOJI,
                "com.javadiscord.jdi.core.annotations.ReactionRemove");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.TYPING_START, "com.javadiscord.jdi.core.annotations.TypingStart");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.CHANNEL_PINS_UPDATE,
                "com.javadiscord.jdi.core.annotations.ChannelPinUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_ROLE_CREATE,
                "com.javadiscord.jdi.core.annotations.GuildRoleCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_ROLE_UPDATE,
                "com.javadiscord.jdi.core.annotations.GuildRoleUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_ROLE_DELETE,
                "com.javadiscord.jdi.core.annotations.GuildRoleDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_CREATE, "com.javadiscord.jdi.core.annotations.ThreadCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_UPDATE, "com.javadiscord.jdi.core.annotations.ThreadUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_DELETE, "com.javadiscord.jdi.core.annotations.ThreadDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_MEMBER_UPDATE,
                "com.javadiscord.jdi.core.annotations.GuildMemberUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_MEMBER_REMOVE,
                "com.javadiscord.jdi.core.annotations.GuildMemberRemove");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_MEMBER_ADD, "com.javadiscord.jdi.core.annotations.GuildMemberAdd");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.INVITE_CREATE, "com.javadiscord.jdi.core.annotations.GuildInviteCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.INVITE_DELETE, "com.javadiscord.jdi.core.annotations.GuildInviteDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.USER_UPDATE, "com.javadiscord.jdi.core.annotations.UserUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_LIST_SYNC, "com.javadiscord.jdi.core.annotations.ThreadListSync");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_MEMBER_UPDATE,
                "com.javadiscord.jdi.core.annotations.ThreadMember");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.THREAD_MEMBERS_UPDATE,
                "com.javadiscord.jdi.core.annotations.ThreadMemberUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_BAN_ADD, "com.javadiscord.jdi.core.annotations.GuildBan");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_BAN_REMOVE, "com.javadiscord.jdi.core.annotations.GuildBanRemove");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.WEBHOOKS_UPDATE, "com.javadiscord.jdi.core.annotations.WebHookUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.VOICE_STATE_UPDATE,
                "com.javadiscord.jdi.core.annotations.VoiceStateUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_DELETE_BULK,
                "com.javadiscord.jdi.core.annotations.MessageBulkDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.MESSAGE_REACTION_REMOVE_ALL,
                "com.javadiscord.jdi.core.annotations.MessageReactionsRemoved");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.STAGE_INSTANCE_CREATE,
                "com.javadiscord.jdi.core.annotations.StageCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.STAGE_INSTANCE_UPDATE,
                "com.javadiscord.jdi.core.annotations.StageUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.STAGE_INSTANCE_DELETE,
                "com.javadiscord.jdi.core.annotations.StageDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.INTERACTION_CREATE,
                "com.javadiscord.jdi.core.annotations.InteractionCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.AUTO_MODERATION_RULE_CREATE,
                "com.javadiscord.jdi.core.annotations.AutoModerationRuleCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.AUTO_MODERATION_RULE_UPDATE,
                "com.javadiscord.jdi.core.annotations.AutoModerationRuleUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.AUTO_MODERATION_RULE_DELETE,
                "com.javadiscord.jdi.core.annotations.AutoModerationRuleDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.AUTO_MODERATION_ACTION_EXECUTION,
                "com.javadiscord.jdi.core.annotations.AutoModerationRuleExecution");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.ENTITLEMENT_CREATE,
                "com.javadiscord.jdi.core.annotations.EntitlementCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.ENTITLEMENT_DELETE,
                "com.javadiscord.jdi.core.annotations.EntitlementDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.ENTITLEMENT_UPDATE,
                "com.javadiscord.jdi.core.annotations.EntitlementUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_SCHEDULED_EVENT_CREATE,
                "com.javadiscord.jdi.core.annotations.ScheduledEventCreate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_SCHEDULED_EVENT_UPDATE,
                "com.javadiscord.jdi.core.annotations.ScheduledEventUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_SCHEDULED_EVENT_DELETE,
                "com.javadiscord.jdi.core.annotations.ScheduledEventDelete");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_SCHEDULED_EVENT_USER_REMOVE,
                "com.javadiscord.jdi.core.annotations.ScheduledEventUserRemove");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_SCHEDULED_EVENT_USER_ADD,
                "com.javadiscord.jdi.core.annotations.ScheduledEventUserAdd");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_STICKERS_UPDATE,
                "com.javadiscord.jdi.core.annotations.StickerUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_INTEGRATIONS_UPDATE,
                "com.javadiscord.jdi.core.annotations.IntegrationUpdate");
        EVENT_TYPE_ANNOTATIONS.put(
                EventType.GUILD_MEMBERS_CHUNK, "com.javadiscord.jdi.core.annotations.MemberChunk");
    }

    private final Discord discord;

    public GatewayEventListenerAnnotations(Discord discord) {
        this.discord = discord;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void receive(EventType eventType, Object event) {
        if (!EVENT_TYPE_ANNOTATIONS.containsKey(eventType)) {
            return;
        }
        Class<? extends Annotation> annotationClass;
        try {
            annotationClass =
                    (Class<? extends Annotation>)
                            Class.forName(EVENT_TYPE_ANNOTATIONS.get(eventType));
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not find annotation binding for {}", eventType);
            return;
        }
        for (Object listener : discord.getAnnotatedEventListeners()) {
            Method[] methods = listener.getClass().getMethods();
            List<Object> paramOrder = new ArrayList<>();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(annotationClass)) {
                    continue;
                }
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    if (parameter.getParameterizedType() == event.getClass()) {
                        paramOrder.add(event);
                    } else if (parameter.getParameterizedType() == Discord.class) {
                        paramOrder.add(discord);
                    } else if (parameter.getParameterizedType() == Guild.class) {
                        Guild guild = GatewayEventListener.getGuild(discord, event);
                        paramOrder.add(guild);
                    }
                }
                try {
                    if (paramOrder.size() != method.getParameterCount()) {
                        throw new RuntimeException(
                                "Bound "
                                        + paramOrder.size()
                                        + " parameters but expected "
                                        + method.getParameterCount());
                    }
                    LOGGER.trace("Invoking method {} with params {}", method.getName(), paramOrder);
                    method.invoke(listener, paramOrder.toArray());
                } catch (Exception e) {
                    LOGGER.error("Failed to invoke {}", method.getName(), e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
