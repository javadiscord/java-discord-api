package com.javadiscord.jdi.core.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerUpdate(
        @JsonProperty("guildId") long guildId, @JsonProperty("stickers") List<Sticker> stickers) {}
