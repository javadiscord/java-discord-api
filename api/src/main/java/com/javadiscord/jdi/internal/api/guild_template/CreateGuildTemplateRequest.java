package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record CreateGuildTemplateRequest(long guildId, String name, Optional<String> description)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        description.ifPresent(d -> body.put("description", d));

        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/templates".formatted(guildId))
                .body(body);
    }
}
