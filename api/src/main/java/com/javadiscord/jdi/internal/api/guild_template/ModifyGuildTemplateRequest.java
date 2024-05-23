package com.javadiscord.jdi.internal.api.guild_template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyGuildTemplateRequest(
    long guildId,
    String templateCode,
    Optional<String> name,
    Optional<String> description
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, String> body = new HashMap<>();
        name.ifPresent(n -> body.put("name", n));
        description.ifPresent(desc -> body.put("description", desc));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/templates/%s".formatted(guildId, templateCode))
            .body(body);
    }
}
