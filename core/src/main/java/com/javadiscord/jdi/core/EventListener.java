package com.javadiscord.jdi.core;

import com.javadiscord.jdi.core.models.application.Application;
import com.javadiscord.jdi.core.models.audit_log.AuditLogEntry;
import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationRule;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.core.models.guild.*;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.core.models.message.*;
import com.javadiscord.jdi.core.models.scheduled_event.EventUser;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEvent;
import com.javadiscord.jdi.core.models.stage.Stage;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.core.models.voice.VoiceServer;
import com.javadiscord.jdi.core.models.voice.VoiceState;
import com.javadiscord.jdi.core.models.webhook.Webhook;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadMember;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.ThreadSync;

public interface EventListener {
    default void onAutoModerationRuleCreate(AutoModerationRule autoModerationRule, Guild guild) {}

    default void onInteractionCreate(Interaction interaction, Guild guild) {}

    default void onEntitlementDelete(Entitlement entitlement, Guild guild) {}

    default void onThreadListSync(ThreadSync threadSync, Guild guild) {}

    default void onStickerUpdate(StickerUpdate stickerUpdate, Guild guild) {}

    default void onThreadUpdate(Thread thread, Guild guild) {}

    default void onGuildBanRemove(GuildBan guildBan, Guild guild) {}

    default void onGuildRoleUpdate(Role role, Guild guild) {}

    default void onUserUpdate(User user, Guild guild) {}

    default void onMessageReactionsRemoved(MessageReactionsRemoved reactionsRemoved, Guild guild) {}

    default void onMessageReactionRemovedAll(
        MessageReactionsRemoved reactionsRemoved,
        Guild guild
    ) {}

    default void onScheduledEventUpdate(ScheduledEvent scheduledEvent, Guild guild) {}

    default void onGuildMemberAdd(Member guildMember, Guild guild) {}

    default void onStageCreate(Stage stage, Guild guild) {}

    default void onScheduledEventCreate(ScheduledEvent scheduledEvent, Guild guild) {}

    default void onEntitlementUpdate(Entitlement entitlement, Guild guild) {}

    default void onChannelDelete(Channel channel, Guild guild) {}

    default void onEntitlementCreate(Entitlement entitlement, Guild guild) {}

    default void onAutoModerationRuleExecution(
        AutoModerationRule autoModerationRule,
        Guild guild
    ) {}

    default void onMessageUpdate(Message message, Guild guild) {}

    default void onScheduledEventUserRemove(EventUser eventUser, Guild guild) {}

    default void onChannelCreate(Channel channel, Guild guild) {}

    default void onStageDelete(Stage stage, Guild guild) {}

    default void onScheduledEventDelete(ScheduledEvent scheduledEvent, Guild guild) {}

    default void onThreadDelete(Thread thread, Guild guild) {}

    default void onStageUpdate(Stage stage, Guild guild) {}

    default void onGuildBan(Object event, Guild guild) {}

    default void onGuildDelete(Guild guild) {}

    default void onReactionRemove(MessageReaction messageReaction, Guild guild) {}

    default void onGuildCreate(Guild guild) {}

    default void onAutoModerationRuleDelete(AutoModerationRule autoModerationRule, Guild guild) {}

    default void onGuildRoleDelete(Role role, Guild guild) {}

    default void onThreadMemberUpdate(ThreadMember threadMember, Guild guild) {}

    default void onWebHookUpdate(Webhook webhook, Guild guild) {}

    default void onMessageBulkDelete(MessageBulkDelete messages, Guild guild) {}

    default void onGuildInviteDelete(Invite invite, Guild guild) {}

    default void onGuildUpdateEvent(Guild guild) {}

    default void onScheduledEventUserAdd(EventUser eventUser, Guild guild) {}

    default void onMessageDelete(Message message, Guild guild) {}

    default void onChannelUpdate(Channel channel, Guild guild) {}

    default void onMessageCreate(Message message, Guild guild) {}

    default void onMemberChunk(Object member, Guild guild) {}

    default void onTypingStart(TypingStart typingStart, Guild guild) {}

    default void onGuildInviteCreate(Invite invite, Guild guild) {}

    default void onAutoModerationRuleUpdate(AutoModerationRule autoModerationRule, Guild guild) {}

    default void onGuildMemberRemove(Member guildMember, Guild guild) {}

    default void onChannelPinUpdate(MessagePin messagePin, Guild guild) {}

    default void onThreadCreate(Thread thread, Guild guild) {}

    default void onGuildMemberUpdate(Member guildMember, Guild guild) {}

    default void onGuildRoleCreate(Role role, Guild guild) {}

    default void onVoiceStateUpdate(VoiceState voiceState, Guild guild) {}

    default void onMessageReactionAdd(MessageReaction messageReaction, Guild guild) {}

    default void onThreadMembersUpdate(ThreadMember threadMember, Guild guild) {}

    default void onGuildIntegrationUpdate(IntegrationUpdate integration, Guild guild) {}

    default void onVoiceServerUpdate(VoiceServer voiceServer, Guild guild) {}

    default void onEmojiUpdate(Emoji emoji, Guild guild) {}

    default void onAuditLogEntryCreate(AuditLogEntry auditLogEntry, Guild guild) {}

    default void onApplicationPermissionCommandUpdate(Application application, Guild guild) {}
}
