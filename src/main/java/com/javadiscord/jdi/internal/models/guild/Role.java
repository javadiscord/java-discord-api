package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Role(
        long id,
        String name,
        String color,
        boolean hoist,
        String icon,
        @JsonProperty("unicode_emoji") String unicodeEmoji,
        int position,
        String permissions,
        boolean managed,
        boolean mentionable,
        Tags tags,
        int flags) {}
