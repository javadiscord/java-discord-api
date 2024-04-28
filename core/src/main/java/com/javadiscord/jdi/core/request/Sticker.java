package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.ModifyGuildStickerBuilder;
import com.javadiscord.jdi.internal.api.sticker.*;

import java.nio.file.Path;

public class Sticker {
    public CreateGuildStickerRequest createGuildSticker(
            long guildId, String name, String description, String tags, Path filePath) {
        return new CreateGuildStickerRequest(guildId, name, description, tags, filePath);
    }

    public DeleteGuildStickerRequest deleteGuildSticker(long guildId, long stickerId) {
        return new DeleteGuildStickerRequest(guildId, stickerId);
    }

    public GetGuildStickerRequest getGuildSticker(long guildId, long stickerId) {
        return new GetGuildStickerRequest(guildId, stickerId);
    }

    public GetGuildStickersRequest getGuildStickers(long guildId) {
        return new GetGuildStickersRequest(guildId);
    }

    public GetStickerPacksRequest getStickerPacks() {
        return new GetStickerPacksRequest();
    }

    public GetStickerRequest getSticker(long stickerId) {
        return new GetStickerRequest(stickerId);
    }

    public ModifyGuildStickerRequest modifyGuildSticker(ModifyGuildStickerBuilder builder) {
        return builder.build();
    }
}
