package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Role(
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("color") String color,
        @JsonProperty("hoist") boolean hoist,
        @JsonProperty("icon") String icon,
        @JsonProperty("unicode_emoji") String unicodeEmoji,
        @JsonProperty("position") int position,
        @JsonProperty("permissions") String permissions,
        @JsonProperty("managed") boolean managed,
        @JsonProperty("mentionable") boolean mentionable,
        @JsonProperty("tags") Tags tags,
        @JsonProperty("flags") int flags) {}
