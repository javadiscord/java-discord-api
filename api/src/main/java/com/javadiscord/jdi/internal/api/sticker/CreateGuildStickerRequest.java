package com.javadiscord.jdi.internal.api.sticker;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import com.javadiscord.jdi.core.api.utils.DiscordImageUtil;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MediaType;
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

        try {
            MultipartBodyPublisher.Builder body =
                MultipartBodyPublisher.newBuilder()
                    .textPart("name", name)
                    .textPart("description", description)
                    .textPart("tags", tags);

            String extension = DiscordImageUtil.getExtension(filePath);

            switch (extension) {
                case "png" -> body.filePart("file", filePath, MediaType.IMAGE_PNG);
                case "jpg", "jpeg" -> body.filePart("file", filePath, MediaType.IMAGE_JPEG);
                case "gif" -> body.filePart("file", filePath, MediaType.IMAGE_GIF);
                default ->
                    throw new IllegalArgumentException("Unsupported extension: " + extension);
            }

            return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/stickers".formatted(guildId))
                .multipartBody(body.build());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
