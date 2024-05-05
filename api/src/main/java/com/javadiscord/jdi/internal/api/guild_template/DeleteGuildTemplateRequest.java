package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record DeleteGuildTemplateRequest(
        long guildId, String templateCode, String name, String description)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/templates/%s".formatted(guildId, templateCode))
                .body(
                        Map.of(
                                "name", name,
                                "description", description));
    }
}
