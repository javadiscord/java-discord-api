package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageBulkDelete(
    @JsonProperty("ids") long[] ids,
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("guild_id") long guildId
) {
}
