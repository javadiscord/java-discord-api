package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.stage.CreateStageRequest;

import java.util.Optional;

public class CreateStageBuilder {
    private final long channelId;
    private final String topic;
    private final int privacyLevel;
    private Optional<Boolean> sendStartNotification;
    private Optional<Long> guildScheduledEventId;

    public CreateStageBuilder(long channelId, String topic, int privacyLevel) {
        this.channelId = channelId;
        this.topic = topic;
        this.privacyLevel = privacyLevel;
        this.sendStartNotification = Optional.empty();
        this.guildScheduledEventId = Optional.empty();
    }

    public CreateStageBuilder sendStartNotification(boolean sendStartNotification) {
        this.sendStartNotification = Optional.of(sendStartNotification);
        return this;
    }

    public CreateStageBuilder guildScheduledEventId(long guildScheduledEventId) {
        this.guildScheduledEventId = Optional.of(guildScheduledEventId);
        return this;
    }

    public CreateStageRequest build() {
        return new CreateStageRequest(
                channelId, topic, privacyLevel, sendStartNotification, guildScheduledEventId);
    }
}
