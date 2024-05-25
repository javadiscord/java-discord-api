package com.javadiscord.jdi.core.api.builders;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentUserVoiceStateRequest;

public final class ModifyCurrentUserVoiceStateBuilder {
    private long guildId;
    private Optional<Long> channelId;
    private Optional<Boolean> suppress;
    private Optional<OffsetDateTime> requestToSpeakTimestamp;

    public ModifyCurrentUserVoiceStateBuilder() {
        this.channelId = Optional.empty();
        this.suppress = Optional.empty();
        this.requestToSpeakTimestamp = Optional.empty();
    }

    public ModifyCurrentUserVoiceStateBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentUserVoiceStateBuilder channelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyCurrentUserVoiceStateBuilder suppress(boolean suppress) {
        this.suppress = Optional.of(suppress);
        return this;
    }

    public ModifyCurrentUserVoiceStateBuilder requestToSpeakTimestamp(
        OffsetDateTime requestToSpeakTimestamp
    ) {
        this.requestToSpeakTimestamp = Optional.of(requestToSpeakTimestamp);
        return this;
    }

    public ModifyCurrentUserVoiceStateRequest build() {
        return new ModifyCurrentUserVoiceStateRequest(
            guildId,
            channelId,
            suppress,
            requestToSpeakTimestamp
        );
    }
}
