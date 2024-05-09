package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.ModifyGuildStickerBuilder;
import com.javadiscord.jdi.core.models.message.Sticker;
import com.javadiscord.jdi.core.models.message.StickerPack;
import com.javadiscord.jdi.internal.api.sticker.*;

import java.nio.file.Path;
import java.util.List;

public class StickerRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public StickerRequest(DiscordResponseParser responseParser, long guildId) {
        this.guildId = guildId;
        this.responseParser = responseParser;
    }

    public AsyncResponse<Sticker> createGuildSticker(
            long guildId, String name, String description, String tags, Path filePath) {
        return responseParser.callAndParse(
                Sticker.class,
                new CreateGuildStickerRequest(guildId, name, description, tags, filePath));
    }

    public AsyncResponse<Sticker> deleteGuildSticker(long stickerId) {
        return responseParser.callAndParse(
                Sticker.class, new DeleteGuildStickerRequest(guildId, stickerId));
    }

    public AsyncResponse<Sticker> getGuildSticker(long stickerId) {
        return responseParser.callAndParse(
                Sticker.class, new GetGuildStickerRequest(guildId, stickerId));
    }

    public AsyncResponse<List<Sticker>> getGuildStickers() {
        return responseParser.callAndParseList(Sticker.class, new GetGuildStickersRequest(guildId));
    }

    public AsyncResponse<List<StickerPack>> getStickerPacks() {
        return responseParser.callAndParseList(StickerPack.class, new GetStickerPacksRequest());
    }

    public AsyncResponse<Sticker> getSticker(long stickerId) {
        return responseParser.callAndParse(Sticker.class, new GetStickerRequest(stickerId));
    }

    public AsyncResponse<Sticker> modifyGuildSticker(ModifyGuildStickerBuilder builder) {
        return responseParser.callAndParse(Sticker.class, builder.guildId(guildId).build());
    }
}
