package com.javadiscord.jdi.internal.models.application;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final record ApplicationRoleConnectionMetadata(
    @JsonProperty("type") ApplicationRoleConnectionMetadataType type,
    @JsonProperty("key") String key,
    @JsonProperty("name") String name,
    @JsonProperty("name_localizations") Map<String, String> nameLocalizations,
    @JsonProperty("description") String description,
    @JsonProperty("description_localizations") Map<String, String> descriptionLocalizations
) {}
