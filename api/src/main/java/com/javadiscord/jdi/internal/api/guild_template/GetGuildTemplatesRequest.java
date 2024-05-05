package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildTemplatesRequest(long guildId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/guilds/%s/templates".formatted(guildId));
    }
}
