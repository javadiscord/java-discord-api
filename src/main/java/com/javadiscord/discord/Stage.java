package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stage {
    private long id;

    @JsonProperty("guild_id")
    private long guildId;

    @JsonProperty("channel_id")
    private long channelId;
    private String topic;

    @JsonProperty("privacy_level")
    private int privacyLevel;

    @JsonProperty("discoverable_disabled")
    private boolean discoverableDisabled;

    @JsonProperty("guild_scheduled_event_id")
    private long guildScheduleEventId;
}
