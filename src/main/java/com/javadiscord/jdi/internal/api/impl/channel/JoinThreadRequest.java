package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record JoinThreadRequest(long channelId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/channels/%s/thread-members/@me".formatted(channelId));
    }
}
