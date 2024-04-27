package com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.channel.ThreadMetadata;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Thread(
        @JsonProperty("type") int type,
        @JsonProperty("total_message_sent") int totalMessageSent,
        @JsonProperty("thread_metadata") ThreadMetadata threadMetadata,
        @JsonProperty("rate_limit_per_user") int rateLimitPerUser,
        @JsonProperty("parent_id") String parentId,
        @JsonProperty("owner_id") String ownerId,
        @JsonProperty("newly_created") boolean newlyCreated,
        @JsonProperty("name") String name,
        @JsonProperty("message_count") int messageCount,
        @JsonProperty("member_count") int memberCount,
        @JsonProperty("last_message_id") String lastMessageId,
        @JsonProperty("id") long id,
        @JsonProperty("guild_id") long guildId,
        @JsonProperty("flags") int flags) {}
