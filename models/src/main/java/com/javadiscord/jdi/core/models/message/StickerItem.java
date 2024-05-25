package com.javadiscord.jdi.core.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerItem(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("format_type") int formatType
) {}
