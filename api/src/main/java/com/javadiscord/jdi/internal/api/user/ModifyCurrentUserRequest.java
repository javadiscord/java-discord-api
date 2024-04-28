package com.javadiscord.jdi.internal.api.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyCurrentUserRequest(String username, Optional<String> avatarImage, String type)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("type", type);
        avatarImage.ifPresent(val -> payload.put("avatar", "%s".formatted(val)));

        return new DiscordRequestBuilder().path("/users/@me").patch().body(payload);
    }
}
