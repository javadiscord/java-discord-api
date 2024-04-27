package com.javadiscord.jdi.internal.models.message.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedFooter(
        @JsonProperty("text") String text,
        @JsonProperty("icon_url") String iconUrl,
        @JsonProperty("proxy_icon_url") String proxyIconUrl) {}
