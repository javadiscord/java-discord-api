package com.javadiscord.jdi.internal.api.sticker;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.nio.file.Path;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

public record CreateGuildStickerRequest(
    long guildId,
    String name,
    String description,
    String tags,
    Path filePath
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        HttpRequest.BodyPublisher body;
        try {
            body =
                MultipartBodyPublisher.newBuilder()
                    .textPart("name", name)
                    .textPart("description", description)
                    .textPart("tags", tags)
                    .filePart("file", filePath)
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/stickers".formatted(guildId))
            .body(body);
    }
}
