package com.javadiscord.jdi.internal.api.impl.stage;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetStageRequest(String channelId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/stage-instances/%s".formatted(channelId));
    }
}
