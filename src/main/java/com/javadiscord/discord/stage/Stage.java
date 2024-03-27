package com.javadiscord.discord.stage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Stage(
        @JsonProperty("id") long id,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("channel_id") long channelId,
        @JsonProperty("topic") String topic,
        @JsonProperty("privacy_level") int privacyLevel,
        @JsonProperty("discoverable_disabled") boolean discoverableDisabled,
        @JsonProperty("guild_scheduled_event_id") long guildScheduleEventId) {}
