package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WidgetSettingsObject(
    @JsonProperty("enabled") boolean enabled,
    @JsonProperty("channel_id") long channelId
) {}
