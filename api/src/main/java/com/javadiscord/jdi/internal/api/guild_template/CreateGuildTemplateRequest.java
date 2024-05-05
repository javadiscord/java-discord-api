package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record CreateGuildTemplateRequest(long guildId, String name, String description)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/templates".formatted(guildId))
                .body(
                        Map.of(
                                "name", name,
                                "description", description));
    }
}
