package com.javadiscord.jdi.internal.api.impl.sticker;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildStickerRequest(long guildId, long stickerId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .get()
                .path("/guilds/%s/stickers/%s".formatted(guildId, stickerId));
    }
}
