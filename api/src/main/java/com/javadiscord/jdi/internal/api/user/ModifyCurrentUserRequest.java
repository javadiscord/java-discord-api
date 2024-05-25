package com.javadiscord.jdi.internal.api.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyCurrentUserRequest(
    String username,
    Optional<String> avatarImage
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        avatarImage.ifPresent(val -> payload.put("avatar", "%s".formatted(val)));

        return new DiscordRequestBuilder().path("/users/@me").patch().body(payload);
    }
}
