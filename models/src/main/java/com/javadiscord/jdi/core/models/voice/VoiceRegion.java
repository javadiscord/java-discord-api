package com.javadiscord.jdi.core.models.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VoiceRegion(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("optimal") boolean optimal,
        @JsonProperty("deprecated") boolean deprecated,
        @JsonProperty("custom") boolean custom) {}
