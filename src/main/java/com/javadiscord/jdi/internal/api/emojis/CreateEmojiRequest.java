package com.javadiscord.jdi.internal.api.emojis;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateEmojiRequest(
    long guildId,
    String name,
    String image, // https://discord.com/developers/docs/reference#image-data
    List<Long> roles
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/emojis".formatted(guildId))
            .body(
                Map.of(
                    "name", name,
                    "image", image,
                    "roles", roles
                )
            );
    }
}
