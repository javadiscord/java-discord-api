package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// https://discord.com/developers/docs/resources/auto-moderation#auto-moderation-action-object-action-metadata
// read this to see when fields will be present
@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationActionMetadata(@JsonProperty("channel_id") long channelId,
        @JsonProperty("duration_seconds") int durationSecond,
        @JsonProperty("custom_message") String customMessage) {}
