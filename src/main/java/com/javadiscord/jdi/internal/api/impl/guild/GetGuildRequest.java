package com.javadiscord.jdi.internal.api.impl.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildRequest(long guildId, Optional<Boolean> withCounts)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        withCounts.ifPresent(val -> body.put("with_counts", val));
        return new DiscordRequestBuilder().get().path("/guilds/%s".formatted(guildId)).body(body);
    }
}
