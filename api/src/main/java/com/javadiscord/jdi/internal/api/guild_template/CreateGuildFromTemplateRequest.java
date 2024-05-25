package com.javadiscord.jdi.internal.api.guild_template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateGuildFromTemplateRequest(
    String templateCode,
    String name,
    Optional<String> icon
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        icon.ifPresent(it -> body.put("icon", it));

        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/templates/%s".formatted(templateCode))
            .body(body);
    }
}
