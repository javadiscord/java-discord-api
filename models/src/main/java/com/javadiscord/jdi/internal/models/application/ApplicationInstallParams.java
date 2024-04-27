package com.javadiscord.jdi.internal.models.application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationInstallParams(
    @JsonProperty("scopes") List<String> scopes,
    @JsonProperty("permissions") String permission
) {}
