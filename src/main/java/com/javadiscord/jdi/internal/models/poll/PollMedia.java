package com.javadiscord.jdi.internal.models.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.emoji.Emoji;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollMedia(
        @JsonProperty("text") String text,
        @JsonProperty("emoji") Emoji emoji
        ) {}