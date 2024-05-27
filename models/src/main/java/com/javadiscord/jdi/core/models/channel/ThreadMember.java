package com.javadiscord.jdi.core.models.channel;

import com.javadiscord.jdi.core.models.guild.GuildMember;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMember(
    @JsonProperty("id") long threadId,
    @JsonProperty("user_id") long userId,
    @JsonProperty(
        "join_timestamp"
    ) String joinTime,
    @JsonProperty("flags") int flags,
    @JsonProperty("member") GuildMember guildMember
) {}
