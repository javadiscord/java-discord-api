package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyUserVoiceStateRequest;

import java.util.Optional;

public final class ModifyUserVoiceStateRequestBuilder {
    private long guildId;
    private final long userId;
    private Optional<Long> channelId;
    private Optional<Boolean> suppress;

    public ModifyUserVoiceStateRequestBuilder(long userId) {
        this.userId = userId;
        this.channelId = Optional.empty();
        this.suppress = Optional.empty();
    }

    public ModifyUserVoiceStateRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyUserVoiceStateRequestBuilder setChannelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyUserVoiceStateRequestBuilder setSuppress(boolean suppress) {
        this.suppress = Optional.of(suppress);
        return this;
    }

    public ModifyUserVoiceStateRequest build() {
        return new ModifyUserVoiceStateRequest(guildId, userId, channelId, suppress);
    }
}
