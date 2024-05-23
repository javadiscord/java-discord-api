package com.javadiscord.jdi.core.models.channel;

import java.time.OffsetDateTime;

import com.javadiscord.jdi.core.models.guild.GuildMember;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMember(
    @JsonProperty("id") long threadId,
    @JsonProperty("user_id") long userId,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "join_timestamp"
    ) OffsetDateTime joinTime,
    @JsonProperty("flags") int flags,
    @JsonProperty("member") GuildMember guildMember
) {}
