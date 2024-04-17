package com.javadiscord.jdi.internal.models.guild;

import java.util.List;

import com.javadiscord.jdi.internal.models.message.Sticker;
import com.javadiscord.jdi.internal.models.user.Member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Guild(@JsonProperty("id") long id, @JsonProperty("name") String name,
        @JsonProperty("icon") String icon, @JsonProperty("splash") String splash,
        @JsonProperty("discovery_splash") String discoverySplash,
        @JsonProperty("owner") boolean owner, @JsonProperty("ownerId") long ownerId,
        @JsonProperty("permissions") String permissions, @JsonProperty("region") String region,
        @JsonProperty("afk_channel_id") long afkChannelId,
        @JsonProperty("afk_timeout") int afkTimeout,
        @JsonProperty("widget_enabled") boolean widgetEnabled,
        @JsonProperty("widget_channel_id") long widgetChannelId,
        @JsonProperty("verification_level") int verificationLevel,
        @JsonProperty("large") boolean large,
        @JsonProperty("embedded_activities") String[] embeddedActivities,
        @JsonProperty("vanity_url_code") String vanityUrlCode,
        @JsonProperty("member_count") int memberCount, @JsonProperty("max_members") int maxMembers,
        @JsonProperty("premium_subscription_count") int premiumSubscriptionCount,
        @JsonProperty("nsfw") boolean nsfw, @JsonProperty("rules_channel_id") long rulesChannelId,
        @JsonProperty("joined_at") String joinDate, @JsonProperty("description") String description,
        @JsonProperty("unavailable") boolean unavailable,
        @JsonProperty("members") List<Member> members, @JsonProperty("banner") String banner,
        @JsonProperty("premium_tier") int premiumTier,
        @JsonProperty("preferred_locale") String preferredLocale,
        @JsonProperty("public_updates_channel_id") long publicUpdateChannelId,
        @JsonProperty("max_video_channel_users") int maxVideoChannelUsers,
        @JsonProperty("max_stage_video_channel_users") int maxStageVideoChannelUsers,
        @JsonProperty("approximate_member_count") int approximateMemberCount,
        @JsonProperty("welcome_screen") WelcomeScreen welcomeScreen,
        @JsonProperty("nsfw_level") int nsfwLevel, @JsonProperty("stickers") List<Sticker> stickers,
        @JsonProperty("premium_progress_bar_enabled") boolean premiumProgressBarEnabled,
        @JsonProperty("safety_alerts_channel_id") long safetyAlertsChannelId) {}
