package com.javadiscord.jdi.internal.models.audit_log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// https://discord.com/developers/docs/resources/audit-log#audit-log-entry-object-optional-audit-entry-info
// read above to see when fields will be here
@JsonIgnoreProperties(ignoreUnknown = true)
public record OptionalAuditEntryInfo(
        @JsonProperty("application_id") long applicationId,
        @JsonProperty("auto_moderation_rule_name") String autoModerationRuleName,
        @JsonProperty("auto_moderation_rule_trigger_type") String autoModerationRuleTriggerType,
        @JsonProperty("channel_id") long channelId,
        @JsonProperty("count") String count,
        @JsonProperty("delete_member_days") String deleteMemberDays,
        @JsonProperty("id") long snowflake,
        @JsonProperty("members_removed") String membersRemoved,
        @JsonProperty("message_id") long messageId,
        @JsonProperty("role_name") String roleName,
        @JsonProperty("type") String type,
        @JsonProperty("integration_type") String integrationType) {}
