package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AllowedMentions(
        @JsonProperty("parse") List<AllowedMentionsType> parse,
        @JsonProperty("roles") List<Long> roles,
        @JsonProperty("users") List<Long> users,
        @JsonProperty("replied_user") boolean repliedUser) {}
