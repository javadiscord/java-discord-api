package com.javadiscord.jdi.core.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerPack(
        long id,
        List<Sticker> stickers,
        String name,
        long sku_id,
        @JsonProperty("cover_sticker_id") long coverStickerId,
        String description,
        @JsonProperty("banner_asset_id") long bannerAssetId) {}
