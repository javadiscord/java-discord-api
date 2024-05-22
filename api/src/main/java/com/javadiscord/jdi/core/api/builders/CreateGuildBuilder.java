package com.javadiscord.jdi.core.api.builders;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.Role;
import com.javadiscord.jdi.internal.api.guild.CreateGuildRequest;

public class CreateGuildBuilder {
    private final String name;
    private Optional<String> icon;
    private Optional<Integer> verificationLevel;
    private Optional<Integer> defaultMessageNotifications;
    private Optional<Integer> explicitContentFilter;
    private Optional<List<Role>> roles;
    private Optional<List<Channel>> channels;
    private Optional<Long> afkChannelId;
    private Optional<Integer> afkTimeout;
    private Optional<Long> systemChannelId;
    private Optional<Integer> systemChannelFlags;

    public CreateGuildBuilder(String name) {
        this.name = name;
        this.icon = Optional.empty();
        this.verificationLevel = Optional.empty();
        this.defaultMessageNotifications = Optional.empty();
        this.explicitContentFilter = Optional.empty();
        this.roles = Optional.empty();
        this.channels = Optional.empty();
        this.afkChannelId = Optional.empty();
        this.afkTimeout = Optional.empty();
        this.systemChannelId = Optional.empty();
        this.systemChannelFlags = Optional.empty();
    }

    public CreateGuildBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public CreateGuildBuilder verificationLevel(int verificationLevel) {
        this.verificationLevel = Optional.of(verificationLevel);
        return this;
    }

    public CreateGuildBuilder defaultMessageNotifications(int defaultMessageNotifications) {
        this.defaultMessageNotifications = Optional.of(defaultMessageNotifications);
        return this;
    }

    public CreateGuildBuilder explicitContentFilter(int explicitContentFilter) {
        this.explicitContentFilter = Optional.of(explicitContentFilter);
        return this;
    }

    public CreateGuildBuilder roles(List<Role> roles) {
        this.roles = Optional.of(roles);
        return this;
    }

    public CreateGuildBuilder channels(List<Channel> channels) {
        this.channels = Optional.of(channels);
        return this;
    }

    public CreateGuildBuilder afkChannelId(long afkChannelId) {
        this.afkChannelId = Optional.of(afkChannelId);
        return this;
    }

    public CreateGuildBuilder afkTimeout(int afkTimeout) {
        this.afkTimeout = Optional.of(afkTimeout);
        return this;
    }

    public CreateGuildBuilder systemChannelId(long systemChannelId) {
        this.systemChannelId = Optional.of(systemChannelId);
        return this;
    }

    public CreateGuildBuilder systemChannelFlags(int systemChannelFlags) {
        this.systemChannelFlags = Optional.of(systemChannelFlags);
        return this;
    }

    public CreateGuildRequest build() {
        return new CreateGuildRequest(
            name,
            icon,
            verificationLevel,
            defaultMessageNotifications,
            explicitContentFilter,
            roles,
            channels,
            afkChannelId,
            afkTimeout,
            systemChannelId,
            systemChannelFlags
        );
    }
}
