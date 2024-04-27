package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ChannelMention(
        @JsonProperty("id") long id,
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("type") int type,
        @JsonProperty("name") String name) {}
