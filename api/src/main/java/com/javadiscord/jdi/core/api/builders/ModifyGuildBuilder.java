package com.javadiscord.jdi.core.api.builders;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.guild.GuildFeature;
import com.javadiscord.jdi.internal.api.guild.ModifyGuildRequest;

public final class ModifyGuildBuilder {
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

    public ModifyGuildBuilder() {
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

    public ModifyGuildBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildBuilder verificationLevel(int verificationLevel) {
        this.verificationLevel = Optional.of(verificationLevel);
        return this;
    }

    public ModifyGuildBuilder defaultMessageNotifications(int defaultMessageNotifications) {
        this.defaultMessageNotifications = Optional.of(defaultMessageNotifications);
        return this;
    }

    public ModifyGuildBuilder explicitContentFilter(int explicitContentFilter) {
        this.explicitContentFilter = Optional.of(explicitContentFilter);
        return this;
    }

    public ModifyGuildBuilder afkChannelId(long afkChannelId) {
        this.afkChannelId = Optional.of(afkChannelId);
        return this;
    }

    public ModifyGuildBuilder afkTimeout(int afkTimeout) {
        this.afkTimeout = Optional.of(afkTimeout);
        return this;
    }

    public ModifyGuildBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public ModifyGuildBuilder ownerId(long ownerId) {
        this.ownerId = Optional.of(ownerId);
        return this;
    }

    public ModifyGuildBuilder splash(String splash) {
        this.splash = Optional.of(splash);
        return this;
    }

    public ModifyGuildBuilder discoverySplash(String discoverySplash) {
        this.discoverySplash = Optional.of(discoverySplash);
        return this;
    }

    public ModifyGuildBuilder banner(String banner) {
        this.banner = Optional.of(banner);
        return this;
    }

    public ModifyGuildBuilder systemChannelId(long systemChannelId) {
        this.systemChannelId = Optional.of(systemChannelId);
        return this;
    }

    public ModifyGuildBuilder systemChannelFlags(int systemChannelFlags) {
        this.systemChannelFlags = Optional.of(systemChannelFlags);
        return this;
    }

    public ModifyGuildBuilder rulesChannelId(long rulesChannelId) {
        this.rulesChannelId = Optional.of(rulesChannelId);
        return this;
    }

    public ModifyGuildBuilder publicUpdatesChannelId(long publicUpdatesChannelId) {
        this.publicUpdatesChannelId = Optional.of(publicUpdatesChannelId);
        return this;
    }

    public ModifyGuildBuilder preferredLocale(String preferredLocale) {
        this.preferredLocale = Optional.of(preferredLocale);
        return this;
    }

    public ModifyGuildBuilder features(List<GuildFeature> features) {
        this.features = Optional.of(features);
        return this;
    }

    public ModifyGuildBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildBuilder premiumProgressBarEnabled(boolean premiumProgressBarEnabled) {
        this.premiumProgressBarEnabled = Optional.of(premiumProgressBarEnabled);
        return this;
    }

    public ModifyGuildBuilder safetyAlertsChannelId(long safetyAlertsChannelId) {
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
            safetyAlertsChannelId
        );
    }
}
