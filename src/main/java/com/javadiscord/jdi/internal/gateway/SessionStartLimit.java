package com.javadiscord.jdi.internal.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SessionStartLimit(
        int total,
        int remaining,
        @JsonProperty("reset_after") long resetAfter,
        @JsonProperty("max_concurrency") int maxConcurrency) {}
