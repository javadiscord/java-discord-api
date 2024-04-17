package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.Member;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TypingStart(
        @JsonProperty("channel_id") String channelId,
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("timestamp") int timestamp,
        @JsonProperty("member") Member member) {}
