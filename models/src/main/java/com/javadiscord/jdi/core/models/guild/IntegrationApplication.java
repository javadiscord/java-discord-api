package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IntegrationApplication(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("icon") String icon,
        @JsonProperty("description") String description,
        @JsonProperty("bot") User bot) {}
