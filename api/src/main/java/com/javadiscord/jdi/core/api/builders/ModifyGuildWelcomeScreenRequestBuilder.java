package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.guild.WelcomeScreenChannel;
import com.javadiscord.jdi.internal.api.guild.ModifyGuildWelcomeScreenRequest;

import java.util.Optional;

public final class ModifyGuildWelcomeScreenRequestBuilder {
    private long guildId;
    private Optional<Boolean> enabled;
    private Optional<WelcomeScreenChannel> welcomeChannels;
    private Optional<String> description;

    public ModifyGuildWelcomeScreenRequestBuilder() {
        this.enabled = Optional.empty();
        this.welcomeChannels = Optional.empty();
        this.description = Optional.empty();
    }

    public ModifyGuildWelcomeScreenRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildWelcomeScreenRequestBuilder setEnabled(boolean enabled) {
        this.enabled = Optional.of(enabled);
        return this;
    }

    public ModifyGuildWelcomeScreenRequestBuilder setWelcomeChannels(
            WelcomeScreenChannel welcomeChannels) {
        this.welcomeChannels = Optional.of(welcomeChannels);
        return this;
    }

    public ModifyGuildWelcomeScreenRequestBuilder setDescription(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildWelcomeScreenRequest build() {
        return new ModifyGuildWelcomeScreenRequest(guildId, enabled, welcomeChannels, description);
    }
}
