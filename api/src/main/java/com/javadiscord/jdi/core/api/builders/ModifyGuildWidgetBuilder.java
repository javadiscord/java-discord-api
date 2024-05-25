package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.ModifyGuildWidgetRequest;

public final class ModifyGuildWidgetBuilder {
    private long guildId;
    private Optional<Boolean> enabled;
    private Optional<Long> channelId;

    public ModifyGuildWidgetBuilder() {
        this.enabled = Optional.empty();
        this.channelId = Optional.empty();
    }

    public ModifyGuildWidgetBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildWidgetBuilder enabled(Boolean enabled) {
        this.enabled = Optional.of(enabled);
        return this;
    }

    public ModifyGuildWidgetBuilder channelId(Long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyGuildWidgetRequest build() {
        return new ModifyGuildWidgetRequest(guildId, enabled, channelId);
    }
}
