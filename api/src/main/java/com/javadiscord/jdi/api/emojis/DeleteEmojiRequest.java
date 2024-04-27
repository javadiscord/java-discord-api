package com.javadiscord.jdi.api.emojis;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record DeleteEmojiRequest(long guildId, long emojiId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/guilds/%s/emojis/%s".formatted(guildId, emojiId));
    }
}
