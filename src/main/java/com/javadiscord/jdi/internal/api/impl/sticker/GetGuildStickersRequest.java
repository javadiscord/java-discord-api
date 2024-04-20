package com.javadiscord.jdi.internal.api.impl.sticker;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildStickersRequest(long guildId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/guilds/%s/stickers".formatted(guildId));
    }
}
