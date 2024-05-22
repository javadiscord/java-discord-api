package com.javadiscord.jdi.internal.api.user;

import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateDMRequest(long recipientId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/users/@me/channels")
            .body(Map.of("recipient_id", recipientId));
    }
}
