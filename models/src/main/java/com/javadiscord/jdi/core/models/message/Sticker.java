package com.javadiscord.jdi.core.models.message;

import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sticker(
    @JsonProperty("id") long id,
    @JsonProperty("pack_id") long packId,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("tags") String tags,
    @JsonProperty("asset") String asset,
    @JsonProperty("type") StickerType type,
    @JsonProperty("format_type") StickerFormatType formatType,
    @JsonProperty("available") boolean available,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("user") User user,
    @JsonProperty("sort_value") int sortValue
) {}
