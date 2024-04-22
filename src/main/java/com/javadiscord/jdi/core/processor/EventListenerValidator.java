package com.javadiscord.jdi.core.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.javadiscord.jdi.core.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventListenerValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Class<? extends Annotation>, String[]> EXPECTED_PARAM_TYPES_MAP = new HashMap<>();

    static {
        EXPECTED_PARAM_TYPES_MAP.put(
            AutoModerationRuleCreate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.AutoModerationRuleObject",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            AutoModerationRuleDelete.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.AutoModerationRuleObject",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            AutoModerationRuleUpdate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.AutoModerationRuleObject",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            EntitlementCreate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.Entitlement",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            EntitlementDelete.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.Entitlement",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            EntitlementUpdate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.Entitlement",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ChannelCreate.class,
            new String[] { "com.javadiscord.jdi.internal.models.channel.Channel", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ChannelUpdate.class,
            new String[] { "com.javadiscord.jdi.internal.models.channel.Channel", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ChannelDelete.class,
            new String[] { "com.javadiscord.jdi.internal.models.channel.Channel", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ScheduledEventCreate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.ScheduledEvent",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ScheduledEventUpdate.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.ScheduledEvent",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ScheduledEventDelete.class,
            new String[] { "com.javadiscord.jdi.internal.models.guild.ScheduledEvent",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildBan.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.GuildBan", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildBanRemove.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.GuildBan", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildInviteCreate.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Invite", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildInviteDelete.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Invite", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildRoleUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Role", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildRoleCreate.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Role", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            GuildRoleDelete.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Role", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            IntegrationUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.IntegrationUpdate",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MessageBulkDelete.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.MessageBulkDelete",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MessageCreate.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.Message", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MessageDelete.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.Message", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MessageReactionsRemoved.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.MessageReactionsRemoved",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MessageUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.Message", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ReactionRemove.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.MessageReaction",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ReactionAdd.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.MessageReaction",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            TypingStart.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.TypingStart",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            StageCreate.class,
            new String[] { " com.javadiscord.jdi.internal.models.stage.Stage", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            StageDelete.class,
            new String[] { " com.javadiscord.jdi.internal.models.stage.Stage", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            StageUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.stage.Stage", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            StickerUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.message.StickerUpdate",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadCreate.class,
            new String[] { " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadDelete.class,
            new String[] { " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadListSync.class,
            new String[] { " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadSync",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadMember.class,
            new String[] { " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMember",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            ThreadMemberUpdate.class,
            new String[] {
                " com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMemberUpdate",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            MemberChunk.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.MemberChunk",
                "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            UserUpdate.class,
            new String[] { " com.javadiscord.jdi.internal.models.user.User", "com.javadiscord.jdi.core.Discord" }
        );

        EXPECTED_PARAM_TYPES_MAP.put(
            InteractionCreate.class,
            new String[] { " com.javadiscord.jdi.internal.models.guild.Interaction",
                "com.javadiscord.jdi.core.Discord" }
        );
    }

    public boolean validate(Class<?> clazz) {
        return hasZeroArgsConstructor(clazz) && validateMethods(clazz);
    }

    private boolean hasZeroArgsConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        LOGGER.error("{} does not have a 0 arg constructor", clazz.getName());
        return false;
    }

    private boolean validateMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            for (Map.Entry<Class<? extends Annotation>, String[]> entry : EXPECTED_PARAM_TYPES_MAP.entrySet()) {
                Class<? extends Annotation> annotationClass = entry.getKey();
                if (method.isAnnotationPresent(annotationClass)) {
                    String[] expectedParamTypes = entry.getValue();
                    if (method.getParameterCount() > 0) {
                        Class<?>[] paramTypes = method.getParameterTypes();
                        for (Class<?> type : paramTypes) {
                            boolean isExpectedType = false;
                            for (String expectedType : expectedParamTypes) {
                                if (type.getName().equals(expectedType)) {
                                    isExpectedType = true;
                                    break;
                                }
                            }
                            if (!isExpectedType) {
                                LOGGER.error("Unexpected parameter found: {}", type.getName());
                                return false;
                            } else {
                                LOGGER.trace("Loaded {}", clazz.getName());
                            }
                        }
                    } else {
                        LOGGER.error("{} does not have the expected parameter types", method.getName());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
