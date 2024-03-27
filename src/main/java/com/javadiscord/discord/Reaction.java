package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Reaction(
        @JsonProperty("count") int count,
        @JsonProperty("count_details") ReactionCountDetails countDetails,
        @JsonProperty("me") boolean me,
        @JsonProperty("me_burst") boolean meBurst,
        @JsonProperty("emoji") Emoji emoji,
        @JsonProperty("burst_colors") List<String> burstColors
) {}
