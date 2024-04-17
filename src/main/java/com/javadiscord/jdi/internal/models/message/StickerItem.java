package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerItem(@JsonProperty("id") String id, @JsonProperty("name") String name,
        @JsonProperty("format_type") int formatType) {}
