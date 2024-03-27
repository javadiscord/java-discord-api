package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Channel(
        @JsonProperty("id") long id,
        @JsonProperty("type") int type,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("position") int position,
        @JsonProperty("permission_overwrites") List<Overwrite> permissionOverwrites,
        @JsonProperty("name") String name,
        @JsonProperty("topic") String topic,
        @JsonProperty("nsfw") boolean nsfw,
        @JsonProperty("last_message_id") long lastMessageId,
        @JsonProperty("bitRate") int bitRate,
        @JsonProperty("user_limit") int userLimit,
        @JsonProperty("rate_limit_per_user") int rateLimitPerUser,
        @JsonProperty("recipients") List<User> recipients,
        @JsonProperty("icon") String icon,
        @JsonProperty("owner_id") long ownerId,
        @JsonProperty("application_id") long applicationId,
        @JsonProperty("managed") boolean managed,
        @JsonProperty("parent_id") long parentId,
        @JsonProperty("last_pin_timestamp") long lastPinTimestamp,
        @JsonProperty("rtc_region") String rtcRegion,
        @JsonProperty("video_quality_mode") int videoQualityMode,
        @JsonProperty("message_count") int messageCount,
        @JsonProperty("member_count") int memberCount,
        @JsonProperty("thread_metadata") ThreadMetadata threadMetadata) {}
