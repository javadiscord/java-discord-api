package com.javadiscord.jdi.api.sticker;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetStickerRequest(long stickerId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/stickers/%s".formatted(stickerId));
    }
}
