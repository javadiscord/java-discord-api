package com.javadiscord.jdi.internal.models.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VoiceServer(
        @JsonProperty("token") String token,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("endpoint") long endpoint) {}
