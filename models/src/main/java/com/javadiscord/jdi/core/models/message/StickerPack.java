package com.javadiscord.jdi.core.models.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerPack(
    @JsonProperty("id") long id,
    @JsonProperty("stickers") List<Sticker> stickers,
    @JsonProperty("name") String name,
    @JsonProperty("sky_id") long skuId,
    @JsonProperty("cover_sticker_id") long coverStickerId,
    @JsonProperty("description") String description,
    @JsonProperty("banner_asset_id") long bannerAssetId
) {}
