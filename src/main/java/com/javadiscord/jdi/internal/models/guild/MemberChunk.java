package com.javadiscord.jdi.internal.models.guild;

import java.util.List;

import com.javadiscord.jdi.internal.gateway.identify.Presence;
import com.javadiscord.jdi.internal.models.user.Member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberChunk(
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("members") List<Member> members,
    @JsonProperty("chunk_index") int chunkIndex,
    @JsonProperty("chunk_count") int chunkCount,
    @JsonProperty("not_found") List<String> notFound,
    @JsonProperty("presences") List<Presence> presences,
    @JsonProperty("nonce") String nonce
) {
}
