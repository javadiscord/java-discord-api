package com.javadiscord.jdi.core.models.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Member(
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("user") User user,
    @JsonProperty("roles") List<String> roles,
    @JsonProperty("joined_at") String joinDate
) {}
