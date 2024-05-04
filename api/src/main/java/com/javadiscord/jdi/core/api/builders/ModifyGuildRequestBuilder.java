package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.guild.GuildFeature;
import com.javadiscord.jdi.internal.api.guild.ModifyGuildRequest;

import java.util.List;
import java.util.Optional;

public final class ModifyGuildRequestBuilder {
    private long guildId;
    private Optional<String> name;
    private Optional<Integer> verificationLevel;
    private Optional<Integer> defaultMessageNotifications;
    private Optional<Integer> explicitContentFilter;
    private Optional<Long> afkChannelId;
    private Optional<Integer> afkTimeout;
    private Optional<String> icon;
    private Optional<Long> ownerId;
    private Optional<String> splash;
    private Optional<String> discoverySplash;
    private Optional<String> banner;
    private Optional<Long> systemChannelId;
    private Optional<Integer> systemChannelFlags;
    private Optional<Long> rulesChannelId;
    private Optional<Long> publicUpdatesChannelId;
    private Optional<String> preferredLocale;
    private Optional<List<GuildFeature>> features;
    private Optional<String> description;
    private Optional<Boolean> premiumProgressBarEnabled;
    private Optional<Long> safetyAlertsChannelId;

    public ModifyGuildRequestBuilder() {
        this.name = Optional.empty();
        this.verificationLevel = Optional.empty();
        this.defaultMessageNotifications = Optional.empty();
        this.explicitContentFilter = Optional.empty();
        this.afkChannelId = Optional.empty();
        this.afkTimeout = Optional.empty();
        this.icon = Optional.empty();
        this.ownerId = Optional.empty();
        this.splash = Optional.empty();
        this.discoverySplash = Optional.empty();
        this.banner = Optional.empty();
        this.systemChannelId = Optional.empty();
        this.systemChannelFlags = Optional.empty();
        this.rulesChannelId = Optional.empty();
        this.publicUpdatesChannelId = Optional.empty();
        this.preferredLocale = Optional.empty();
        this.features = Optional.empty();
        this.description = Optional.empty();
        this.premiumProgressBarEnabled = Optional.empty();
        this.safetyAlertsChannelId = Optional.empty();
    }

    public ModifyGuildRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildRequestBuilder verificationLevel(int verificationLevel) {
        this.verificationLevel = Optional.of(verificationLevel);
        return this;
    }

    public ModifyGuildRequestBuilder defaultMessageNotifications(int defaultMessageNotifications) {
        this.defaultMessageNotifications = Optional.of(defaultMessageNotifications);
        return this;
    }

    public ModifyGuildRequestBuilder explicitContentFilter(int explicitContentFilter) {
        this.explicitContentFilter = Optional.of(explicitContentFilter);
        return this;
    }

    public ModifyGuildRequestBuilder afkChannelId(long afkChannelId) {
        this.afkChannelId = Optional.of(afkChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder afkTimeout(int afkTimeout) {
        this.afkTimeout = Optional.of(afkTimeout);
        return this;
    }

    public ModifyGuildRequestBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public ModifyGuildRequestBuilder ownerId(long ownerId) {
        this.ownerId = Optional.of(ownerId);
        return this;
    }

    public ModifyGuildRequestBuilder splash(String splash) {
        this.splash = Optional.of(splash);
        return this;
    }

    public ModifyGuildRequestBuilder discoverySplash(String discoverySplash) {
        this.discoverySplash = Optional.of(discoverySplash);
        return this;
    }

    public ModifyGuildRequestBuilder banner(String banner) {
        this.banner = Optional.of(banner);
        return this;
    }

    public ModifyGuildRequestBuilder systemChannelId(long systemChannelId) {
        this.systemChannelId = Optional.of(systemChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder systemChannelFlags(int systemChannelFlags) {
        this.systemChannelFlags = Optional.of(systemChannelFlags);
        return this;
    }

    public ModifyGuildRequestBuilder rulesChannelId(long rulesChannelId) {
        this.rulesChannelId = Optional.of(rulesChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder publicUpdatesChannelId(long publicUpdatesChannelId) {
        this.publicUpdatesChannelId = Optional.of(publicUpdatesChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder preferredLocale(String preferredLocale) {
        this.preferredLocale = Optional.of(preferredLocale);
        return this;
    }

    public ModifyGuildRequestBuilder features(List<GuildFeature> features) {
        this.features = Optional.of(features);
        return this;
    }

    public ModifyGuildRequestBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildRequestBuilder premiumProgressBarEnabled(boolean premiumProgressBarEnabled) {
        this.premiumProgressBarEnabled = Optional.of(premiumProgressBarEnabled);
        return this;
    }

    public ModifyGuildRequestBuilder safetyAlertsChannelId(long safetyAlertsChannelId) {
        this.safetyAlertsChannelId = Optional.of(safetyAlertsChannelId);
        return this;
    }

    public ModifyGuildRequest build() {
        return new ModifyGuildRequest(
                guildId,
                name,
                verificationLevel,
                defaultMessageNotifications,
                explicitContentFilter,
                afkChannelId,
                afkTimeout,
                icon,
                ownerId,
                splash,
                discoverySplash,
                banner,
                systemChannelId,
                systemChannelFlags,
                rulesChannelId,
                publicUpdatesChannelId,
                preferredLocale,
                features,
                description,
                premiumProgressBarEnabled,
                safetyAlertsChannelId);
    }
}
