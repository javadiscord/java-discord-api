package com.javadiscord.jdi.internal.models.message.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedField(
    @JsonProperty("name") String name,
    @JsonProperty("value") String value,
    @JsonProperty("inline") boolean inline
) {
}
