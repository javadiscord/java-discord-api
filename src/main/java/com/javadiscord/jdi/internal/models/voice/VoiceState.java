package com.javadiscord.jdi.internal.models.voice;

import com.javadiscord.jdi.internal.models.user.Member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VoiceState(
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("user_id") long userId,
    @JsonProperty("member") Member member,
    @JsonProperty("session_id") String sessionId,
    @JsonProperty("deaf") boolean deaf,
    @JsonProperty("mute") boolean mute,
    @JsonProperty("self_deaf") boolean selfDeaf,
    @JsonProperty("self_mute") boolean selfMute,
    @JsonProperty("self_stream") boolean selfStream,
    @JsonProperty("self_video") boolean selfVideo,
    @JsonProperty("suppress") boolean suppress,
    @JsonProperty("request_to_speak_timestamp") String requestToSpeakTimestamp
) {
}
