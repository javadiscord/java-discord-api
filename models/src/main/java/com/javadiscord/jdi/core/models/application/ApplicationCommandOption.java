package com.javadiscord.jdi.core.models.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandOption(
        @JsonProperty("name") String name,
        @JsonProperty("type") int type,
        @JsonProperty("value") Object value,
        @JsonProperty("options") List<ApplicationCommandOption> options,
        @JsonProperty("focused") boolean focused) {}
