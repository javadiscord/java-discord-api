package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageInteractionMetadata(
        @JsonProperty("id") String id,
        @JsonProperty("type") int type,
        @JsonProperty("user_id") String userId,
        @JsonProperty("authorizing_integration_owners") Map<String, String> authorizingIntegrationOwners,
        @JsonProperty("original_response_message_id") String originalResponseMessageId,
        @JsonProperty("interacted_message_id") String interactedMessageId,
        @JsonProperty("triggering_interaction_metadata") MessageInteractionMetadata triggeringInteractionMetadata
) {}
