package com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMemberUpdate(@JsonProperty("guild_id") String guildId,
        @JsonProperty("thread_member") ThreadMember threadMember) {}
