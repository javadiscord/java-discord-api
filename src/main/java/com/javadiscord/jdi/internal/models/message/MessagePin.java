package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessagePin(
    @JsonProperty("last_pin_timestamp") String lastPinTimestamp,
    @JsonProperty("channel_id") String channelId,
    @JsonProperty("guild_id") String guildId
) {
}
