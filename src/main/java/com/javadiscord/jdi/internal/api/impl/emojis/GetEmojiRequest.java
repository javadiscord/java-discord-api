package com.javadiscord.jdi.internal.api.impl.emojis;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetEmojiRequest(long guildId, long emojiId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/guilds/%s/emojis/%s".formatted(guildId, emojiId));
    }
}
