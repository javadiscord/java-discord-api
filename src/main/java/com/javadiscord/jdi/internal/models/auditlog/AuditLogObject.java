package com.javadiscord.jdi.internal.models.auditlog;

import java.util.List;

import com.javadiscord.jdi.internal.models.automoderation.AutoModerationRule;
import com.javadiscord.jdi.internal.models.guild.Integration;
import com.javadiscord.jdi.internal.models.scheduledevent.ScheduledEvent;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * https://discord.com/developers/docs/resources/audit-log#audit-log-object
 * Missing:
 * application_commands
 * threads
 * webhooks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AuditLogObject(
    @JsonProperty("audit_log_entries") List<AuditLogEntry> auditLogEntries,
    @JsonProperty("auto_moderation_rules") List<AutoModerationRule> autoModerationRules,
    @JsonProperty("guild_scheduled_events") List<ScheduledEvent> scheduledEvents,
    @JsonProperty("integrations") List<Integration> integrations,
    @JsonProperty("users") List<User> users
) {}
