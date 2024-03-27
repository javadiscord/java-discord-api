package com.javadiscord.gateway.handlers.events.codec.ready;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        boolean verified,
        String username,
        @JsonProperty("mfa_enabled") boolean mfaEnabled,
        String id,
        @JsonProperty("global_name") String globalName,
        int flags,
        String email,
        String discriminator,
        boolean bot,
        String avatar) {}
