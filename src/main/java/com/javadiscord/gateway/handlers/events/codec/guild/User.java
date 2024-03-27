package com.javadiscord.gateway.handlers.events.codec.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        String username,
        @JsonProperty("public_flags") int publicFlags,
        String id,
        @JsonProperty("global_name") String globalUsername,
        @JsonProperty("display_name") String displayName,
        String discriminator,
        boolean bot,
        String avatar) {}
