package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChannelMention(
        @JsonProperty("id") String id,
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("type") int type,
        @JsonProperty("name") String name
) {}
