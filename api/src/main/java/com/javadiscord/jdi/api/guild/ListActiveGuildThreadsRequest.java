package com.javadiscord.jdi.api.guild;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record ListActiveGuildThreadsRequest(long guildId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/guilds/%s/threads/active".formatted(guildId));
    }
}
