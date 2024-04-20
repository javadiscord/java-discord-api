package com.javadiscord.jdi.internal.api.impl.user;

import java.util.Base64;
import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record ModifyCurrentUserRequest(String username, byte[] avatarImage, String type) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        String encodedAvatar = "data:image/%s;base64,%s".formatted(type, Base64.getEncoder().encodeToString(avatarImage));

        return new DiscordRequestBuilder()
            .path("/users/@me")
            .patch()
            .body(
                Map.of(
                    "username", username,
                    "avatar", encodedAvatar
                )
            );
    }
}
