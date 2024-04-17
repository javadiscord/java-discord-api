package com.javadiscord.jdi.internal.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GatewayEvent(
    @JsonProperty("op") int opcode,
    @JsonProperty("d") JsonNode data,
    @JsonProperty("s") int sequenceNumber,
    @JsonProperty("t") String eventName
) {
}
