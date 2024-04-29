package com.javadiscord.jdi.internal.api.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record CreateDMRequest(long recipientId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/users/@me/channels")
                .body(Map.of("recipient_id", recipientId));
    }
}
