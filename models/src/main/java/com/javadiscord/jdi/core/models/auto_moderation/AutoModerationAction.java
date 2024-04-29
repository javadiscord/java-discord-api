package com.javadiscord.jdi.core.models.auto_moderation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationAction(
        @JsonProperty("type") AutoModerationActionType type,
        @JsonProperty("metadata") AutoModerationActionMetadata metadata) {}
