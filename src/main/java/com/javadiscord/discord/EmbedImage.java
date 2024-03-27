package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedImage(
        @JsonProperty("url") String url,
        @JsonProperty("proxy_url") String proxyUrl,
        @JsonProperty("height") Integer height,
        @JsonProperty("width") Integer width
) {}
