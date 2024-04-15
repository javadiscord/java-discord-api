package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationActionMetadata(
        @JsonProperty("channel_id")
                long channelId, // only preset when action type is SEND_ALERT_MESSAGE
        @JsonProperty("duration_seconds")
                int durationSecond, // only present when action type is TIMEOUT
        @JsonProperty("custom_message")
                String customMessage) {} // only present when action type is BLOCK_MESSAGE and is
                                          // nullable
