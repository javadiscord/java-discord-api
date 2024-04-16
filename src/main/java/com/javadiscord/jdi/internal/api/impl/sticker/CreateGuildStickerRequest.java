package com.javadiscord.jdi.internal.api.impl.sticker;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record CreateGuildStickerRequest(
        long guildId,
        String name,
        String description,
        String tags,
        Object file // TODO: figure out how we will handle the `file content` type
        ) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/stickers".formatted(guildId))
                .body(
                        Map.of(
                                "name", name,
                                "description", description,
                                "tags", tags,
                                "file", file
                        )
                );
    }
}
