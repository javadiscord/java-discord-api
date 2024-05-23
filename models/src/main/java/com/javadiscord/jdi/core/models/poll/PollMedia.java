package com.javadiscord.jdi.core.models.poll;

import com.javadiscord.jdi.core.models.emoji.Emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollMedia(@JsonProperty("text") String text, @JsonProperty("emoji") Emoji emoji) {}
