package com.javadiscord.jdi.api.channel;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record JoinThreadRequest(long channelId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/channels/%s/thread-members/@me".formatted(channelId));
    }
}
