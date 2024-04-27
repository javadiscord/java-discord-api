package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageReference(
        @JsonProperty("messageId") String messageId,
        @JsonProperty("channel_id") String channelId,
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("fail_if_not_exists") boolean failIfNotExists) {}
