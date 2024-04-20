package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationActionObject(
    @JsonProperty("type") int type, // AutoModeraetionActionType
    @JsonProperty("metadata") AutoModerationActionMetadata metadata
) {}
