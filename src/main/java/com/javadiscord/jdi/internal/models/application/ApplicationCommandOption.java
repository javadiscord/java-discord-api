package com.javadiscord.jdi.internal.models.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandOption(
    @JsonProperty("name") String name,
    @JsonProperty("type") int type,
    @JsonProperty("value") Object value,
    @JsonProperty("options") ApplicationCommandOption[] options,
    @JsonProperty("focused") boolean focused
) {}
