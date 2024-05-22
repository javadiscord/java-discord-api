package com.javadiscord.jdi.core.models.message;

import java.util.List;

import com.javadiscord.jdi.core.models.emoji.Emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageReaction(
    @JsonProperty("count") int count,
    @JsonProperty("count_details") ReactionCountDetails countDetails,
    @JsonProperty("me") boolean me,
    @JsonProperty("me_burst") boolean meBurst,
    @JsonProperty("emoji") Emoji emoji,
    @JsonProperty("burst_colors") List<String> burstColors
) {}
