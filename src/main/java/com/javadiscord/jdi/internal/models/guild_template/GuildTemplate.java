package com.javadiscord.jdi.internal.models.guild_template;

import java.time.OffsetDateTime;

import com.javadiscord.jdi.internal.models.guild.Guild;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GuildTemplate(
    @JsonProperty("code") String code,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("usage_count") int usageCount,
    @JsonProperty("creator_id") long creatorId,
    @JsonProperty("creator") User creator,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    ) @JsonProperty("created_at") OffsetDateTime createdAt,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    ) @JsonProperty("updated_at") OffsetDateTime updatedAt,
    @JsonProperty("source_guild_id") long sourceGuildId,
    @JsonProperty("serialized_source_guild") Guild sourceGuild,
    @JsonProperty("is_dirty") boolean isDirty
) {}
