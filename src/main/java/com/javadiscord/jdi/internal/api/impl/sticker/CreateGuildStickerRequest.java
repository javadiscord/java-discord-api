package com.javadiscord.jdi.internal.api.impl.sticker;

import java.net.http.HttpRequest;
import java.nio.file.Path;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

public record CreateGuildStickerRequest(
    long guildId, String name, String description, String tags, Path filePath
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        HttpRequest.BodyPublisher body = null;
        try {
            body = MultipartBodyPublisher.newBuilder()
                .textPart("name", name)
                .textPart("description", description)
                .textPart("tags", tags)
                .filePart("file", filePath)
                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/stickers".formatted(guildId))
            .body(body);
    }
}
