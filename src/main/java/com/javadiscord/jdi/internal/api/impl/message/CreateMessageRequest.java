package com.javadiscord.jdi.internal.api.impl.message;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record CreateMessageRequest(long channelId, String content) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .path("/channels/%s/messages".formatted(channelId))
                .post()
                .body(Map.of("content", content));
    }
}
