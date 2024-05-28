package com.javadiscord.bot.utils.jshell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JShellSnippet(
    @JsonProperty("statement") String statement,
    @JsonProperty("value") String value,
    @JsonProperty("status") String status
) {}
