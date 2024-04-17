package com.javadiscord.jdi.internal.api.impl.sticker;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.util.Map;

public record CreateGuildStickerRequest(
        long guildId,
        String name,
        String description,
        String tags,
        Path filePath
        ) implements DiscordRequest {

    private static final Logger LOGGER = LogManager.getLogger(CreateGuildStickerRequest.class);

    @Override
    public DiscordRequestBuilder create() {
        HttpRequest.BodyPublisher fileContent = HttpRequest.BodyPublishers.ofFile(filePath);
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/stickers".formatted(guildId))
                .body(
                        Map.of(
                                "name", name,
                                "description", description,
                                "tags", tags,
                                "file", fileContent));
    }
}
