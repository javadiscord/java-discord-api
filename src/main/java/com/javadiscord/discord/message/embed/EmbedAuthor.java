package com.javadiscord.discord.message.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedAuthor(
        @JsonProperty("name") String name,
        @JsonProperty("url") String url,
        @JsonProperty("icon_url") String iconUrl,
        @JsonProperty("proxy_icon_url") String proxyIconUrl) {}
