package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.core.models.message.Sticker;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Guild(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("icon") String icon,
        @JsonProperty("icon_hash") String iconHash,
        @JsonProperty("splash") String splash,
        @JsonProperty("discovery_splash") String discoverySplash,
        @JsonProperty("owner") boolean owner,
        @JsonProperty("owner_id") long ownerId,
        @JsonProperty("permissions") String permissions,
        @JsonProperty("region") String region,
        @JsonProperty("afk_channel_id") long afkChannelId,
        @JsonProperty("afk_timeout") int afkTimeout,
        @JsonProperty("widget_enabled") boolean widgetEnabled,
        @JsonProperty("widget_channel_id") long widgetChannelId,
        @JsonProperty("verification_level") int verificationLevel,
        @JsonProperty("default_message_notifications")
                DefaultMessageNotificationLevel defaultMessageNotifications,
        @JsonProperty("explicit_content_filter") ExplicitContentFilterLevel explicitContentFilter,
        @JsonProperty("roles") List<Role> roles,
        @JsonProperty("emojis") List<Emoji> emojis,
        @JsonProperty("features") List<GuildFeature> features,
        @JsonProperty("mfa_level") MFALevel mfaLevel,
        @JsonProperty("application_id") long applicationId,
        @JsonProperty("system_channel_id") long systemChannelId,
        @JsonProperty("system_channel_flags") int systemChannelFlags,
        @JsonProperty("rules_channel_id") long rulesChannelId,
        @JsonProperty("max_presences") int maxPresences,
        @JsonProperty("max_members") int maxMembers,
        @JsonProperty("vanity_url_code") String vanityUrlCode,
        @JsonProperty("description") String description,
        @JsonProperty("banner") String banner,
        @JsonProperty("premium_tier") PremiumTier premiumTier,
        @JsonProperty("premium_subscription_count") int premiumSubscriptionCount,
        @JsonProperty("preferred_locale") String preferredLocale,
        @JsonProperty("public_updates_channel_id") long publicUpdatesChannelId,
        @JsonProperty("max_video_channel_users") int maxVideoChannelUsers,
        @JsonProperty("max_stage_video_channel_users") int maxStageVideoChannelUsers,
        @JsonProperty("approximate_member_count") int approximateMemberCount,
        @JsonProperty("approximate_presence_count") int approximatePresenceCount,
        @JsonProperty("welcome_screen") WelcomeScreen welcomeScreen,
        @JsonProperty("nsfw_level") GuildNSFWLevel nsfwLevel,
        @JsonProperty("stickers") List<Sticker> stickers,
        @JsonProperty("premium_progress_bar_enabled") boolean premiumProgressBarEnabled,
        @JsonProperty("safety_alerts_channel_id") long safetyAlertsChannelId) {}
