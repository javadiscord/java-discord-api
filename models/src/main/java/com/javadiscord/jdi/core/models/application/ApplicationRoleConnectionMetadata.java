package com.javadiscord.jdi.core.models.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationRoleConnectionMetadata(
        @JsonProperty("type") ApplicationRoleConnectionMetadataType type,
        @JsonProperty("key") String key,
        @JsonProperty("name") String name,
        @JsonProperty("name_localizations") Map<String, String> nameLocalizations,
        @JsonProperty("description") String description,
        @JsonProperty("description_localizations") Map<String, String> descriptionLocalizations) {}
