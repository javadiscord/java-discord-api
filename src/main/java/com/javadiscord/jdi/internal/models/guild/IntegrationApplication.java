package com.javadiscord.jdi.internal.models.guild;

import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IntegrationApplication(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("icon") String icon,
    @JsonProperty("description") String description,
    @JsonProperty("bot") User bot
) {}
