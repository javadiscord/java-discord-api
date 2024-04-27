package com.javadiscord.jdi.api.stage;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetStageRequest(String channelId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/stage-instances/%s".formatted(channelId));
    }
}
