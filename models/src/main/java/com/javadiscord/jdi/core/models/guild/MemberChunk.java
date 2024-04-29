package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.identify.Presence;
import com.javadiscord.jdi.core.models.user.Member;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberChunk(
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("members") List<Member> members,
        @JsonProperty("chunk_index") int chunkIndex,
        @JsonProperty("chunk_count") int chunkCount,
        @JsonProperty("not_found") List<String> notFound,
        @JsonProperty("presences") List<Presence> presences,
        @JsonProperty("nonce") String nonce) {}
