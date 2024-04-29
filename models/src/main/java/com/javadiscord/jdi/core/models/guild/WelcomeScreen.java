package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WelcomeScreen(
        @JsonProperty("description") String description,
        @JsonProperty("welcome_channels") List<WelcomeScreenChannel> welcomeScreenChannels) {}
