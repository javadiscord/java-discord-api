package com.javadiscord.jdi.internal.models.message;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageInteractionMetadata(
    @JsonProperty("id") long id,
    @JsonProperty("type") int type,
    @JsonProperty("user_id") String userId,
    @JsonProperty("authorizing_integration_owners") Map<String, String> authorizingIntegrationOwners,
    @JsonProperty("original_response_message_id") String originalResponseMessageId,
    @JsonProperty("interacted_message_id") String interactedMessageId,
    @JsonProperty("triggering_interaction_metadata") MessageInteractionMetadata triggeringInteractionMetadata
) {}
