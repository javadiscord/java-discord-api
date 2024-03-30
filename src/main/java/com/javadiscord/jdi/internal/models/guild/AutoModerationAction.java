package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationAction(
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("action") AutoModerationActionObject action,
        @JsonProperty("rule_id") long ruleId,
        @JsonProperty("rule_trigger_type") String ruleTriggerType,
        @JsonProperty("user_id") long userId,
        @JsonProperty("channel_id") Long channelId,
        @JsonProperty("message_id") Long messageId,
        @JsonProperty("alert_system_message_id") Long alertSystemMessageId,
        @JsonProperty("content") String content,
        @JsonProperty("matched_keyword") String matchedKeyword,
        @JsonProperty("matched_content") String matchedContent) {}
