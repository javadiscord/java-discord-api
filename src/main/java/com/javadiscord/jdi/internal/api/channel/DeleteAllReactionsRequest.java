package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteAllReactionsRequest(long channelId, long messageId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/channels/%s/messages/%s/reactions".formatted(channelId, messageId));
    }
}
