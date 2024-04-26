package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetPinnedMessagesRequest(long channelId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/channels/%s/pins".formatted(channelId));
    }
}
