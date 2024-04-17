package com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMember(@JsonProperty("id") long id, @JsonProperty("guild_id") long guildId,
        @JsonProperty("member_count") int memberCount,
        @JsonProperty("added_members") List<ThreadMember> addedMembers,
        @JsonProperty("removed_member_ids") List<Long> removedMemberIds) {}
