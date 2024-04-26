package com.javadiscord.jdi.internal.models.message.embed;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Embed(
    @JsonProperty("title") String title,
    @JsonProperty("type") String type,
    @JsonProperty("description") String description,
    @JsonProperty("url") String url,
    @JsonProperty(
        "timestamp"
    ) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date timestamp,
    @JsonProperty("color") Integer color,
    @JsonProperty("footer") EmbedFooter footer,
    @JsonProperty("image") EmbedImage image,
    @JsonProperty("thumbnail") EmbedThumbnail thumbnail,
    @JsonProperty("video") EmbedVideo video,
    @JsonProperty("provider") EmbedProvider provider,
    @JsonProperty("author") EmbedAuthor author,
    @JsonProperty("fields") List<EmbedField> fields
) {}
