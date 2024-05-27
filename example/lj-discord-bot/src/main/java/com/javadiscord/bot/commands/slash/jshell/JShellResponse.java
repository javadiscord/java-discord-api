package com.javadiscord.bot.commands.slash.jshell;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JShellResponse(
    @JsonProperty("errorStream") String errorStream,
    @JsonProperty("outputStream") String outputStream,
    @JsonProperty("events") List<JShellSnippet> events,
    @JsonProperty("error") String error
) {}
