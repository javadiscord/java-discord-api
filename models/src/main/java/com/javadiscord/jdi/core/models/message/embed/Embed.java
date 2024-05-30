package com.javadiscord.jdi.core.models.message.embed;

import java.awt.*;
import java.util.ArrayList;
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
    @JsonProperty("timestamp") @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX"
    ) Date timestamp,
    @JsonProperty("color") Integer color,
    @JsonProperty("footer") EmbedFooter footer,
    @JsonProperty("image") EmbedImage image,
    @JsonProperty("thumbnail") EmbedThumbnail thumbnail,
    @JsonProperty("video") EmbedVideo video,
    @JsonProperty("provider") EmbedProvider provider,
    @JsonProperty("author") EmbedAuthor author,
    @JsonProperty("fields") List<EmbedField> fields
) {
    public static class Builder {
        private String title;
        private String type;
        private String description;
        private String url;
        private Date timestamp;
        private Integer color;
        private EmbedFooter footer;
        private EmbedImage image;
        private EmbedThumbnail thumbnail;
        private EmbedVideo video;
        private EmbedProvider provider;
        private EmbedAuthor author;
        private List<EmbedField> fields = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder color(Color color) {
            this.color = color.getRGB() & 0xFFFFFF;
            return this;
        }

        public Builder footer(EmbedFooter footer) {
            this.footer = footer;
            return this;
        }

        public Builder footer(String content) {
            this.footer = new EmbedFooter(content, null, null);
            return this;
        }

        public Builder image(String imageURL) {
            this.image = new EmbedImage(imageURL, null, null, null);
            return this;
        }

        public Builder image(EmbedImage image) {
            this.image = image;
            return this;
        }

        public Builder thumbnail(String imageURL) {
            this.thumbnail = new EmbedThumbnail(imageURL, null, null, null);
            return this;
        }

        public Builder thumbnail(EmbedThumbnail thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder video(EmbedVideo video) {
            this.video = video;
            return this;
        }

        public Builder provider(EmbedProvider provider) {
            this.provider = provider;
            return this;
        }

        public Builder author(EmbedAuthor author) {
            this.author = author;
            return this;
        }

        public Builder fields(List<EmbedField> fields) {
            this.fields = fields;
            return this;
        }

        public Builder addField(EmbedField field) {
            this.fields.add(field);
            return this;
        }

        public Embed build() {
            return new Embed(
                title,
                type,
                description,
                url,
                timestamp,
                color,
                footer,
                image,
                thumbnail,
                video,
                provider,
                author,
                fields
            );
        }
    }
}
