package com.javadiscord.jdi.internal.models.automoderation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationAction(
    @JsonProperty("type") AutoModerationActionType type,
    @JsonProperty("metadata") AutoModerationActionMetadata metadata
) {}