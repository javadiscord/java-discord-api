package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record CreateGuildFromTemplateRequest(String templateCode, String name, String icon)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/templates/%s".formatted(templateCode))
                .body(
                        Map.of(
                                "name", name,
                                "icon", icon));
    }
}
