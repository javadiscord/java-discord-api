package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.Member;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadSync(
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("channel_ids") long[] channelIds,
        List<Channel> threads,
        List<Member> members) {}
