package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.stage.ModifyStageRequest;

import java.util.Optional;

public class ModifyStageBuilder {
    private final String channelId;
    private Optional<String> topic;
    private Optional<Integer> privacyLevel;

    public ModifyStageBuilder(String channelId) {
        this.channelId = channelId;
        topic = Optional.empty();
        privacyLevel = Optional.empty();
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
