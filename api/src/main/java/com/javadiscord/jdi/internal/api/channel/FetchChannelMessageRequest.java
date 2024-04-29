package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record FetchChannelMessageRequest(long channelId, long messageId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .get()
                .path("/channel/%s/messages/%s".formatted(channelId, messageId));
    }
}
