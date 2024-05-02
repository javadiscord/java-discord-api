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

    public ModifyGuildRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildRequestBuilder setVerificationLevel(int verificationLevel) {
        this.verificationLevel = Optional.of(verificationLevel);
        return this;
    }

    public ModifyGuildRequestBuilder setDefaultMessageNotifications(
            int defaultMessageNotifications) {
        this.defaultMessageNotifications = Optional.of(defaultMessageNotifications);
        return this;
    }

    public ModifyGuildRequestBuilder setExplicitContentFilter(int explicitContentFilter) {
        this.explicitContentFilter = Optional.of(explicitContentFilter);
        return this;
    }

    public ModifyGuildRequestBuilder setAfkChannelId(long afkChannelId) {
        this.afkChannelId = Optional.of(afkChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder setAfkTimeout(int afkTimeout) {
        this.afkTimeout = Optional.of(afkTimeout);
        return this;
    }

    public ModifyGuildRequestBuilder setIcon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public ModifyGuildRequestBuilder setOwnerId(long ownerId) {
        this.ownerId = Optional.of(ownerId);
        return this;
    }

    public ModifyGuildRequestBuilder setSplash(String splash) {
        this.splash = Optional.of(splash);
        return this;
    }

    public ModifyGuildRequestBuilder setDiscoverySplash(String discoverySplash) {
        this.discoverySplash = Optional.of(discoverySplash);
        return this;
    }

    public ModifyGuildRequestBuilder setBanner(String banner) {
        this.banner = Optional.of(banner);
        return this;
    }

    public ModifyGuildRequestBuilder setSystemChannelId(long systemChannelId) {
        this.systemChannelId = Optional.of(systemChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder setSystemChannelFlags(int systemChannelFlags) {
        this.systemChannelFlags = Optional.of(systemChannelFlags);
        return this;
    }

    public ModifyGuildRequestBuilder setRulesChannelId(long rulesChannelId) {
        this.rulesChannelId = Optional.of(rulesChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder setPublicUpdatesChannelId(long publicUpdatesChannelId) {
        this.publicUpdatesChannelId = Optional.of(publicUpdatesChannelId);
        return this;
    }

    public ModifyGuildRequestBuilder setPreferredLocale(String preferredLocale) {
        this.preferredLocale = Optional.of(preferredLocale);
        return this;
    }

    public ModifyGuildRequestBuilder setFeatures(List<GuildFeature> features) {
        this.features = Optional.of(features);
        return this;
    }

    public ModifyGuildRequestBuilder setDescription(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildRequestBuilder setPremiumProgressBarEnabled(
            boolean premiumProgressBarEnabled) {
        this.premiumProgressBarEnabled = Optional.of(premiumProgressBarEnabled);
        return this;
    }

    public ModifyGuildRequestBuilder setSafetyAlertsChannelId(long safetyAlertsChannelId) {
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
