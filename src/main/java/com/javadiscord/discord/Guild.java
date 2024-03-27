package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.gateway.handlers.events.codec.Event;
import com.javadiscord.gateway.handlers.events.codec.guild.Member;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Guild implements Event {
    private long id;
    private String name;
    private String icon;
    private String slash;

    @JsonProperty("discovery_splash")
    private String discoverySplash;

    private boolean owner;

    @JsonProperty("ownerId")
    private long ownerId;

    private String permissions;

    private String region;

    @JsonProperty("afk_channel_id")
    private long afkChannelId;

    @JsonProperty("afk_timeout")
    private int afkTimeout;

    @JsonProperty("widget_enabled")
    private boolean widgetEnabled;

    @JsonProperty("widget_channel_id")
    private long widgetChannelId;

    @JsonProperty("verification_level")
    private int verificationLevel;

    @JsonProperty("large")
    private boolean large;

    @JsonProperty("embedded_activities")
    private String[] embeddedActivities;

    @JsonProperty("vanity_url_code")
    private String vanityUrlCode;

    @JsonProperty("member_count")
    private int memberCount;

    @JsonProperty("max_members")
    private int maxMembers;

    @JsonProperty("premium_subscription_count")
    private int premiumSubscriptionCount;

    @JsonProperty("nsfw")
    private boolean nsfw;

    @JsonProperty("rules_channel_id")
    private long rulesChannelId;

    @JsonProperty("joined_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    private Date joinDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("unavailable")
    private boolean unavailable;

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("banner")
    private String banner;

    @JsonProperty("premium_tier")
    private int premiumTier;

    @JsonProperty("preferred_locale")
    private String preferredLocale;

    @JsonProperty("public_updates_channel_id")
    private long publicUpdateChannelId;

    @JsonProperty("max_video_channel_users")
    public int maxVideoChannelUsers;

    @JsonProperty("max_stage_video_channel_users")
    private int maxStageVideoChannelUsers;

    @JsonProperty("approximate_member_count")
    private int approximateMemberCount;

    @JsonProperty("welcome_screen")
    private WelcomeScreen welcomeScreen;

    @JsonProperty("nsfw_level")
    private int nsfwLevel;

    @JsonProperty("stickers")
    private List<Sticker> stickers;

    @JsonProperty("premium_progress_bar_enabled")
    private boolean premiumProgressBarEnabled;

    @JsonProperty("safety_alerts_channel_id")
    private long safetyAlertsChannelId;
}
