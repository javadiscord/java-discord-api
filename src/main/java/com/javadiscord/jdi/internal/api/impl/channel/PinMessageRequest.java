package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record PinMessageRequest(long channelId, long messageId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/channels/%s/pins/%s".formatted(channelId, messageId));
    }
}
