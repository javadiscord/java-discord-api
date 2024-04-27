package com.javadiscord.jdi.api.user;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetCurrentUserGuildMemberRequest(long guildId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/users/@me/guilds/%d/member".formatted(guildId));
    }
}
