package com.javadiscord.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Gateway(
        String url,
        int shards,
        @JsonProperty("session_start_limit") SessionStartLimit sessionStartLimit) {}
