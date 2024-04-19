package com.javadiscord.jdi.internal.models.automod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread;
import com.javadiscord.jdi.internal.models.guild.AutoModerationRuleObject;
import com.javadiscord.jdi.internal.models.guild.Integration;
import com.javadiscord.jdi.internal.models.guild.ScheduledEvent;
import com.javadiscord.jdi.internal.models.user.User;

import java.util.List;

/*
 * https://discord.com/developers/docs/resources/audit-log#audit-log-object
 * Missing:
 * application_commands
 * threads
 * webhooks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AuditLogObject(
        @JsonProperty("audit_log_entries") List<AuditLogEntryObject> auditLogEntries,
        @JsonProperty("auto_moderation_rules") List<AutoModerationRuleObject> autoModerationRules,
        @JsonProperty("guild_scheduled_events") List<ScheduledEvent> scheduledEvents,
        @JsonProperty("integrations") List<Integration> integrations,
        @JsonProperty("users") List<User> users
        ) {}