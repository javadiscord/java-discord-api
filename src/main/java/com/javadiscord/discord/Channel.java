package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    private long id;
    private int type;

    @JsonProperty("guild_id")
    private long guildId;

    private int position;

    @JsonProperty("permission_overwrites")
    private List<Overwrite> permissionOverwrites;

    private String name;
    private String topic;

    private boolean nsfw;

    @JsonProperty("last_message_id")
    private long lastMessageId;

    @JsonProperty("bitRate")
    private int bitRate;

    @JsonProperty("user_limit")
    private int userLimit;

    @JsonProperty("rate_limit_per_user")
    private int rateLimitPerUser;

    private List<User> recipients;

    private String icon;

    @JsonProperty("owner_id")
    private long ownerId;

    @JsonProperty("application_id")
    private long applicationId;

    private boolean managed;

    @JsonProperty("parent_id")
    private long parentId;

    @JsonProperty("last_pin_timestamp")
    private long lastPinTimestamp;

    @JsonProperty("rtc_region")
    private String rtcRegion;

    @JsonProperty("video_quality_mode")
    private int videoQualityMode;

    @JsonProperty("message_count")
    private int messageCount;

    @JsonProperty("member_count")
    private int memberCount;

    @JsonProperty("thread_metadata")
    private ThreadMetadata threadMetadata;
}
