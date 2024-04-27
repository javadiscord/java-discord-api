package com.javadiscord.jdi.api.guild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.GuildFeature;

public record ModifyGuildRequest(
    long guildId,
    Optional<String> name,
    Optional<Integer> verificationLevel,
    Optional<Integer> defaultMessageNotifications,
    Optional<Integer> explicitContentFilter,
    Optional<Long> afkChannelId,
    Optional<Integer> afkTimeout,
    Optional<String> icon,
    Optional<Long> ownerId,
    Optional<String> splash,
    Optional<String> discoverySplash,
    Optional<String> banner,
    Optional<Long> systemChannelId,
    Optional<Integer> systemChannelFlags,
    Optional<Long> rulesChannelId,
    Optional<Long> publicUpdatesChannelId,
    Optional<String> preferredLocale,
    Optional<List<GuildFeature>> features,
    Optional<String> description,
    Optional<Boolean> premiumProgressBarEnabled,
    Optional<Long> safetyAlertsChannelId
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        verificationLevel.ifPresent(val -> body.put("verification_level", val));
        defaultMessageNotifications.ifPresent(
            val -> body.put("default_message_notifications", val)
        );
        explicitContentFilter.ifPresent(val -> body.put("explicit_content_filter", val));
        afkChannelId.ifPresent(val -> body.put("afk_channel_id", val));
        afkTimeout.ifPresent(val -> body.put("afk_timeout", val));
        icon.ifPresent(val -> body.put("icon", val));
        ownerId.ifPresent(val -> body.put("owner_id", val));
        splash.ifPresent(val -> body.put("splash", val));
        discoverySplash.ifPresent(val -> body.put("discovery_splash", val));
        banner.ifPresent(val -> body.put("banner", val));
        systemChannelId.ifPresent(val -> body.put("system_channel_id", val));
        systemChannelFlags.ifPresent(val -> body.put("system_channel_flags", val));
        rulesChannelId.ifPresent(val -> body.put("rules_channel_id", val));
        publicUpdatesChannelId.ifPresent(val -> body.put("public_updates_channel_id", val));
        preferredLocale.ifPresent(val -> body.put("preferred_locale", val));
        features.ifPresent(val -> body.put("features", val));
        description.ifPresent(val -> body.put("description", val));
        premiumProgressBarEnabled.ifPresent(val -> body.put("premium_progress_bar_enabled", val));
        safetyAlertsChannelId.ifPresent(val -> body.put("safety_alerts_channel_id", val));

        return new DiscordRequestBuilder().patch().path("/guilds/%s".formatted(guildId)).body(body);
    }
}
