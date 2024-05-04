package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PruneCount(@JsonProperty("pruned") int pruned) {}
