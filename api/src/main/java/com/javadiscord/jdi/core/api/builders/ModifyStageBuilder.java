package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.stage.ModifyStageRequest;

import java.util.Optional;

public class ModifyStageBuilder {
    private final long channelId;
    private Optional<String> topic;
    private Optional<Integer> privacyLevel;

    public ModifyStageBuilder(long channelId) {
        this.channelId = channelId;
        this.topic = Optional.empty();
        this.privacyLevel = Optional.empty();
    }

    public ModifyStageBuilder topic(String topic) {
        this.topic = Optional.of(topic);
        return this;
    }

    public ModifyStageBuilder privacyLevel(int privacyLevel) {
        this.privacyLevel = Optional.of(privacyLevel);
        return this;
    }

    public ModifyStageRequest build() {
        return new ModifyStageRequest(channelId, topic, privacyLevel);
    }
}
