package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Overwrite(
    @JsonProperty("id") long id,
    @JsonProperty("type") int type,
    @JsonProperty("allow") String allow,
    @JsonProperty("deny") String deny
) {}
