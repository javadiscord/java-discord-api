package com.javadiscord.jdi.internal.models.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * https://discord.com/developers/docs/resources/application-role-connection-metadata#application-role-connection-metadata
 * Missing:
 * name_localizations
 * description_localizations
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationRoleConnectionMetadataObject(
        @JsonProperty("type") ApplicationRoleConnectionMetadataType type,
        @JsonProperty("key") String key,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description
) {}