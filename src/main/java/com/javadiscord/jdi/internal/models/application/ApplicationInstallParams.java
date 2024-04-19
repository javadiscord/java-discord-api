package com.javadiscord.jdi.internal.models.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationInstallParamsObject(
        @JsonProperty("scopes") List<String > scopes,
        @JsonProperty("permissions") String permission
) {}