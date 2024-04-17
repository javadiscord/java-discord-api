package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.User;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Emoji(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("roles") List<Role> roles,
        @JsonProperty("user") User user,
        @JsonProperty("require_colons") boolean requiresColons,
        @JsonProperty("managed") boolean managed,
        @JsonProperty("animated") boolean animated,
        @JsonProperty("available") boolean available) {}
