package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IntegrationAccount(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name
) { }
