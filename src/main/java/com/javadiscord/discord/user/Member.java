package com.javadiscord.discord.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Member(User user, String[] roles, @JsonProperty("joined_at") String joinDate) {}
