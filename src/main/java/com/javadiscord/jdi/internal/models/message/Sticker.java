package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sticker(
        @JsonProperty("id") long id,
        @JsonProperty("pack_id") long packId,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("tags") String tags,
        @JsonProperty("asset") String asset,
        @JsonProperty("type") int type,
        @JsonProperty("format_type") int formatType,
        @JsonProperty("available") boolean available,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("user") User user,
        @JsonProperty("sort_value") int sortValue) {}
