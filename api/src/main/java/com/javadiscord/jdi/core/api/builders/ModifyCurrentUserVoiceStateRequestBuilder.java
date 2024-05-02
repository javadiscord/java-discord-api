package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentUserVoiceStateRequest;

import java.time.OffsetDateTime;
import java.util.Optional;

public final class ModifyCurrentUserVoiceStateRequestBuilder {
    private long guildId;
    private Optional<Long> channelId;
    private Optional<Boolean> suppress;
    private Optional<OffsetDateTime> requestToSpeakTimestamp;

    public ModifyCurrentUserVoiceStateRequestBuilder() {
        this.channelId = Optional.empty();
        this.suppress = Optional.empty();
        this.requestToSpeakTimestamp = Optional.empty();
    }

    public ModifyCurrentUserVoiceStateRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentUserVoiceStateRequestBuilder setChannelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyCurrentUserVoiceStateRequestBuilder setSuppress(boolean suppress) {
        this.suppress = Optional.of(suppress);
        return this;
    }

    public ModifyCurrentUserVoiceStateRequestBuilder setRequestToSpeakTimestamp(
            OffsetDateTime requestToSpeakTimestamp) {
        this.requestToSpeakTimestamp = Optional.of(requestToSpeakTimestamp);
        return this;
    }

    public ModifyCurrentUserVoiceStateRequest build() {
        return new ModifyCurrentUserVoiceStateRequest(
                guildId, channelId, suppress, requestToSpeakTimestamp);
    }
}
