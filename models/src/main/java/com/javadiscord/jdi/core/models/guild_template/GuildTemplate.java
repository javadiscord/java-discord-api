package com.javadiscord.jdi.core.models.guild_template;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.core.models.user.User;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GuildTemplate(
        @JsonProperty("code") String code,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("usage_count") int usageCount,
        @JsonProperty("creator_id") long creatorId,
        @JsonProperty("creator") User creator,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
                @JsonProperty("created_at")
                OffsetDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
                @JsonProperty("updated_at")
                OffsetDateTime updatedAt,
        @JsonProperty("source_guild_id") long sourceGuildId,
        @JsonProperty("serialized_source_guild") Guild sourceGuild,
        @JsonProperty("is_dirty") boolean isDirty) {}
