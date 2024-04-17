package com.javadiscord.jdi.internal.models.guild;

import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ScheduledEvent(@JsonProperty("id") long id, @JsonProperty("guild_id") long guildId,
        @JsonProperty("channel_id") long channelId, @JsonProperty("creator_id") long creatorId,
        @JsonProperty("name") String name, @JsonProperty("description") String description,
        @JsonProperty("scheduled_start_time") String scheduledStartTime,
        @JsonProperty("scheduled_end_time") String scheduledEndTime,
        @JsonProperty("privacy_level") PrivacyLevel privacyLevel,
        @JsonProperty("status") EventStatus status,
        @JsonProperty("entity_type") ScheduledEntityType entityType,
        @JsonProperty("entity_id") Long entityId,
        @JsonProperty("entity_metadata") EntityMetadata entityMetadata,
        @JsonProperty("creator") User creator, @JsonProperty("user_count") Integer userCount,
        @JsonProperty("image") String image) {}
