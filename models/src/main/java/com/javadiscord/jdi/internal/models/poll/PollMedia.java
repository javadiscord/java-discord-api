package com.javadiscord.jdi.internal.models.poll;

import com.javadiscord.jdi.internal.models.emoji.Emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollMedia(
    @JsonProperty("text") String text,
    @JsonProperty("emoji") Emoji emoji
) {}
