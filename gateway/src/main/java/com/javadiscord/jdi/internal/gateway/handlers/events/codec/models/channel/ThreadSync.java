package com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.user.Member;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadSync(
        @JsonProperty("guild_id") String guildId,
        @JsonProperty("channel_ids") List<Long> channelIds,
        List<Channel> threads,
        List<Member> members) {}
