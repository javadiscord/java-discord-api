package com.javadiscord.jdi.api.sticker;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record DeleteGuildStickerRequest(long guildId, long stickerId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/guilds/%s/stickers/%s".formatted(guildId, stickerId));
    }
}
