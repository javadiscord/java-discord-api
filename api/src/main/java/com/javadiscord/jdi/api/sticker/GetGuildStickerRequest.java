package com.javadiscord.jdi.api.sticker;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetGuildStickerRequest(long guildId, long stickerId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/guilds/%s/stickers/%s".formatted(guildId, stickerId));
    }
}
