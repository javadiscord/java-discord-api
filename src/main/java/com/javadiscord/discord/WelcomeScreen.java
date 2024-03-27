package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeScreen {
    private String description;

    @JsonProperty("welcome_channels")
    private List<WelcomeScreenChannel> welcomeScreenChannels;
}
