package com.javadiscord.jdi.core.models.guild;

import java.util.List;

import com.javadiscord.jdi.core.models.emoji.Emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OnboardingPromptOption(
    @JsonProperty("id") long id,
    @JsonProperty("channel_ids") List<Long> channelIds,
    @JsonProperty("role_ids") List<Long> roleIds,
    @JsonProperty("emoji") Emoji emoji,
    @JsonProperty("emoji_id") long emojiId,
    @JsonProperty("emoji_name") String emojiName,
    @JsonProperty("emoji_animated") boolean emojiAnimated,
    @JsonProperty("title") String title,
    @JsonProperty("description") String description
) {}
