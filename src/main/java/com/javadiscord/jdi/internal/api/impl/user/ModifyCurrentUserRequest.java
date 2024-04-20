package com.javadiscord.jdi.internal.api.impl.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record ModifyCurrentUserRequest(String username, Path avatarPath) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        byte[] avatarContent = new byte[0];

        try {
            avatarContent = Files.readAllBytes(avatarPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encodedAvatar = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(avatarContent);

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
