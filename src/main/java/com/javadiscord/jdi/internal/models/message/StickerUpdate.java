package com.javadiscord.jdi.internal.models.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerUpdate(@JsonProperty("guildId") long guildId,
        @JsonProperty("stickers") List<Sticker> stickers) {}
