package com.javadiscord.discord.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.discord.user.Member;
import com.javadiscord.discord.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageInteraction(
        @JsonProperty("id") String id,
        @JsonProperty("type") int type,
        @JsonProperty("name") String name,
        @JsonProperty("user") User user,
        @JsonProperty("member") Member member) {}
