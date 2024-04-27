package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WelcomeScreenChannel(
        @JsonProperty("channel_id") long channelId,
        @JsonProperty("description") String description,
        @JsonProperty("emoji_id") long emojiId,
        @JsonProperty("emoji_name") String emojiName) {}
