package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyGuildWidgetRequest;

import java.util.Optional;

public final class ModifyGuildWidgetRequestBuilder {
    private long guildId;
    private Optional<Boolean> enabled;
    private Optional<Long> channelId;

    public ModifyGuildWidgetRequestBuilder() {
        this.enabled = Optional.empty();
        this.channelId = Optional.empty();
    }

    public ModifyGuildWidgetRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildWidgetRequestBuilder enabled(Boolean enabled) {
        this.enabled = Optional.of(enabled);
        return this;
    }

    public ModifyGuildWidgetRequestBuilder channelId(Long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyGuildWidgetRequest build() {
        return new ModifyGuildWidgetRequest(guildId, enabled, channelId);
    }
}
