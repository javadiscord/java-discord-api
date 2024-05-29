package com.javadiscord.jdi.internal.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Gateway(
    @JsonProperty("url") String url,
    @JsonProperty("shards") int shards,
    @JsonProperty("session_start_limit") SessionStartLimit sessionStartLimit
) {}
