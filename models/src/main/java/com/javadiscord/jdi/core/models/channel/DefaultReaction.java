package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DefaultReaction(
        @JsonProperty("emoji_id") long emojiId, @JsonProperty("emoji_name") String emojiName) {}
