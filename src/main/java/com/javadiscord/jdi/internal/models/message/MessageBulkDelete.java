package com.javadiscord.jdi.internal.models.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageBulkDelete(
    @JsonProperty("ids") List<Long> ids,
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("guild_id") long guildId
) {}
