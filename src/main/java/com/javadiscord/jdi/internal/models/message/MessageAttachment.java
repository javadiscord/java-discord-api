package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageAttachment(
    @JsonProperty("id") String id,
    @JsonProperty("filename") String filename,
    @JsonProperty("description") String description,
    @JsonProperty("content_type") String contentType,
    @JsonProperty("size") int size,
    @JsonProperty("url") String url,
    @JsonProperty("proxy_url") String proxyUrl,
    @JsonProperty("height") int height,
    @JsonProperty("width") int width,
    @JsonProperty("ephemeral") boolean ephemeral,
    @JsonProperty("duration_secs") float durationSecs,
    @JsonProperty("waveform") String waveform,
    @JsonProperty("flags") int flags
) {}
