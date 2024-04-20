package com.javadiscord.jdi.internal.api.impl.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record GetCurrentUserGuildMemberRequest(long guildId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/users/@me/guilds/%d/member".formatted(guildId));
    }
}
