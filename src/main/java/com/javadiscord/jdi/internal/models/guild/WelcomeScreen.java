package com.javadiscord.jdi.internal.models.guild;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WelcomeScreen(@JsonProperty("description") String description,
        @JsonProperty("welcome_channels") List<WelcomeScreenChannel> welcomeScreenChannels) {}
