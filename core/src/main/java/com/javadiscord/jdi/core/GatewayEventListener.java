package com.javadiscord.jdi.core;

import java.lang.reflect.Field;

import com.javadiscord.jdi.core.models.application.Application;
import com.javadiscord.jdi.core.models.audit_log.AuditLogEntry;
import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationRule;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.core.models.guild.*;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.core.models.message.*;
import com.javadiscord.jdi.core.models.ready.ReadyEvent;
import com.javadiscord.jdi.core.models.scheduled_event.EventUser;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEvent;
import com.javadiscord.jdi.core.models.stage.Stage;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.core.models.voice.VoiceServer;
import com.javadiscord.jdi.core.models.voice.VoiceState;
import com.javadiscord.jdi.core.models.webhook.Webhook;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.handlers.events.EventType;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.GatewayObserver;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMember;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadSync;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GatewayEventListener implements GatewayObserver {
    private static final Logger LOGGER = LogManager.getLogger(GatewayEventListener.class);
    private final Discord discord;

    public GatewayEventListener(Discord discord) {
        this.discord = discord;
    }

    public static Guild getGuild(Discord discord, Object event) {
        if (event instanceof GuildModel guildModel) {
            return createGuildFromEvent(
                discord, guildModel
            );
        } else {
            return createGuildFromEventObject(discord, event);
        }
    }

    private static Guild createGuildFromEvent(
        Discord discord,
        GuildModel guildEvent
    ) {
        return new Guild(guildEvent, discord.getCache(), discord);
    }

    private static Guild createGuildFromEventObject(Discord discord, Object event) {
        Cache cache = discord.getCache();
        Guild guild = null;
        try {
            long guildId = extractGuildId(event);
            GuildModel model =
                (GuildModel) cache.getCacheForGuild(guildId)
                    .get(guildId, GuildModel.class);
            guild = new Guild(model, cache, discord);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.debug("{} did not come with a guildId field", event.getClass().getSimpleName());
        }

        return guild;
    }

    private static long extractGuildId(
        Object event
    ) throws NoSuchFieldException, IllegalAccessException {
        Field guildIdField = event.getClass().getDeclaredField("guildId");
        guildIdField.setAccessible(true);

        if (guildIdField.getType() == String.class) {
            return Long.parseLong((String) guildIdField.get(event));
        } else {
            return (long) guildIdField.get(event);
        }
    }

    @Override
    public void receive(EventType eventType, Object event) {
        if (eventType == EventType.READY) {
            discord.handleReadyEvent((ReadyEvent) event);
        }

        Guild guild = getGuild(discord, event);

        for (EventListener listener : discord.getEventListeners()) {
            switch (eventType) {
                case GUILD_ROLE_CREATE -> listener.onGuildRoleCreate((Role) event, guild);
                case GUILD_ROLE_UPDATE -> listener.onGuildRoleUpdate((Role) event, guild);
                case GUILD_ROLE_DELETE -> listener.onGuildRoleDelete((Role) event, guild);
                case TYPING_START -> listener.onTypingStart((TypingStart) event, guild);
                case USER_UPDATE -> listener.onUserUpdate((User) event, guild);
                case GUILD_CREATE -> listener.onGuildCreate(guild);
                case GUILD_DELETE -> listener.onGuildDelete(guild);
                case GUILD_UPDATE -> listener.onGuildUpdateEvent(guild);
                case GUILD_BAN_ADD -> listener.onGuildBan(event, guild);
                case GUILD_BAN_REMOVE -> listener.onGuildBanRemove((GuildBan) event, guild);
                case INVITE_CREATE -> listener.onGuildInviteCreate((Invite) event, guild);
                case INVITE_DELETE -> listener.onGuildInviteDelete((Invite) event, guild);
                case THREAD_CREATE -> listener.onThreadCreate((Thread) event, guild);
                case THREAD_DELETE -> listener.onThreadDelete((Thread) event, guild);
                case THREAD_UPDATE -> listener.onThreadUpdate((Thread) event, guild);
                case CHANNEL_CREATE -> listener.onChannelCreate((Channel) event, guild);
                case CHANNEL_DELETE -> listener.onChannelDelete((Channel) event, guild);
                case CHANNEL_UPDATE -> listener.onChannelUpdate((Channel) event, guild);
                case MESSAGE_CREATE -> listener.onMessageCreate((Message) event, guild);
                case MESSAGE_DELETE -> listener.onMessageDelete((Message) event, guild);
                case MESSAGE_UPDATE -> listener.onMessageUpdate((Message) event, guild);
                case WEBHOOKS_UPDATE -> listener.onWebHookUpdate((Webhook) event, guild);
                case GUILD_MEMBER_ADD -> listener.onGuildMemberAdd((Member) event, guild);
                case GUILD_MEMBER_REMOVE -> listener.onGuildMemberRemove((Member) event, guild);
                case GUILD_MEMBER_UPDATE -> listener.onGuildMemberUpdate((Member) event, guild);
                case THREAD_LIST_SYNC -> listener.onThreadListSync((ThreadSync) event, guild);
                case ENTITLEMENT_CREATE -> listener.onEntitlementCreate((Entitlement) event, guild);
                case ENTITLEMENT_DELETE -> listener.onEntitlementDelete((Entitlement) event, guild);
                case ENTITLEMENT_UPDATE -> listener.onEntitlementUpdate((Entitlement) event, guild);
                case INTERACTION_CREATE -> listener.onInteractionCreate((Interaction) event, guild);
                case VOICE_STATE_UPDATE -> listener.onVoiceStateUpdate((VoiceState) event, guild);
                case CHANNEL_PINS_UPDATE -> listener.onChannelPinUpdate((MessagePin) event, guild);
                case GUILD_MEMBERS_CHUNK -> listener.onMemberChunk(event, guild);
                case MESSAGE_DELETE_BULK ->
                    listener.onMessageBulkDelete((MessageBulkDelete) event, guild);
                case VOICE_SERVER_UPDATE ->
                    listener.onVoiceServerUpdate((VoiceServer) event, guild);
                case MESSAGE_REACTION_ADD ->
                    listener.onMessageReactionAdd((MessageReaction) event, guild);
                case THREAD_MEMBER_UPDATE ->
                    listener.onThreadMemberUpdate((ThreadMember) event, guild);
                case GUILD_STICKERS_UPDATE ->
                    listener.onStickerUpdate((StickerUpdate) event, guild);
                case STAGE_INSTANCE_CREATE -> listener.onStageCreate((Stage) event, guild);
                case STAGE_INSTANCE_DELETE -> listener.onStageDelete((Stage) event, guild);
                case STAGE_INSTANCE_UPDATE -> listener.onStageUpdate((Stage) event, guild);
                case THREAD_MEMBERS_UPDATE ->
                    listener.onThreadMembersUpdate((ThreadMember) event, guild);
                case MESSAGE_REACTION_REMOVE ->
                    listener.onMessageReactionsRemoved((MessageReactionsRemoved) event, guild);
                case GUILD_INTEGRATIONS_UPDATE ->
                    listener.onGuildIntegrationUpdate((IntegrationUpdate) event, guild);
                case AUTO_MODERATION_RULE_CREATE ->
                    listener.onAutoModerationRuleCreate((AutoModerationRule) event, guild);
                case AUTO_MODERATION_RULE_DELETE ->
                    listener.onAutoModerationRuleDelete((AutoModerationRule) event, guild);
                case AUTO_MODERATION_RULE_UPDATE ->
                    listener.onAutoModerationRuleUpdate((AutoModerationRule) event, guild);
                case MESSAGE_REACTION_REMOVE_ALL ->
                    listener.onMessageReactionRemovedAll(
                        (MessageReactionsRemoved) event, guild
                    );
                case GUILD_SCHEDULED_EVENT_CREATE ->
                    listener.onScheduledEventCreate((ScheduledEvent) event, guild);
                case GUILD_SCHEDULED_EVENT_DELETE ->
                    listener.onScheduledEventDelete((ScheduledEvent) event, guild);
                case GUILD_SCHEDULED_EVENT_UPDATE ->
                    listener.onScheduledEventUpdate((ScheduledEvent) event, guild);
                case MESSAGE_REACTION_REMOVE_EMOJI ->
                    listener.onReactionRemove((MessageReaction) event, guild);
                case GUILD_SCHEDULED_EVENT_USER_ADD ->
                    listener.onScheduledEventUserAdd((EventUser) event, guild);
                case AUTO_MODERATION_ACTION_EXECUTION ->
                    listener.onAutoModerationRuleExecution((AutoModerationRule) event, guild);
                case GUILD_SCHEDULED_EVENT_USER_REMOVE ->
                    listener.onScheduledEventUserRemove((EventUser) event, guild);
                case GUILD_EMOJIS_UPDATE -> listener.onEmojiUpdate((Emoji) event, guild);
                case GUILD_AUDIT_LOG_ENTRY_CREATE ->
                    listener.onAuditLogEntryCreate((AuditLogEntry) event, guild);
                case APPLICATION_COMMAND_PERMISSIONS_UPDATE ->
                    listener.onApplicationPermissionCommandUpdate((Application) event, guild);
                default -> LOGGER.trace("Unexpected value {}", eventType);
            }
        }
    }
}
