package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.User;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Channel(
        @JsonProperty("id") long id,
        @JsonProperty("type") ChannelType type,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("position") int position,
        @JsonProperty("permission_overwrites") List<Overwrite> permissionOverwrites,
        @JsonProperty("name") String name,
        @JsonProperty("topic") String topic,
        @JsonProperty("nsfw") boolean nsfw,
        @JsonProperty("last_message_id") long lastMessageId,
        @JsonProperty("bitrate") int bitrate,
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
        @JsonProperty("video_quality_mode") VideoQualityMode videoQualityMode,
        @JsonProperty("message_count") int messageCount,
        @JsonProperty("member_count") int memberCount,
        @JsonProperty("thread_metadata") ThreadMetadata threadMetadata,
        @JsonProperty("member") ThreadMember member,
        @JsonProperty("default_auto_archive_duration") int defaultAutoArchiveDuration,
        @JsonProperty("permissions") String permissions,
        @JsonProperty("flags") int flags,
        @JsonProperty("total_message_sent") int threadMessageSent,
        @JsonProperty("available_tags") List<ForumTag> availableTags,
        @JsonProperty("applied_tags") List<Long> appliedTags,
        @JsonProperty("default_reaction_emoji") DefaultReaction defaultReactionEmoji,
        @JsonProperty("default_thread_rate_limit_per_user") int defaultThreadRateLimitPerUser,
        @JsonProperty("default_sort_order") SortOrderType defaultSortOrder,
        @JsonProperty("default_forum_layout") ForumLayoutType defaultForumLayout) {}
