package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationActionMetadata(
        @JsonProperty("channel_id") long channelId,
        @JsonProperty("duration_seconds") int durationSecond,
        @JsonProperty("custom_message") String customMessage) {}
