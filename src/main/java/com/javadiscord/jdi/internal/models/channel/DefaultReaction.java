package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DefaultReaction(
        @JsonProperty("emoji_id") long emojiId, @JsonProperty("emoji_name") String emojiName) {}
