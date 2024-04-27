package com.javadiscord.jdi.internal.api.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetCurrentUserGuildsRequest(
    Optional<Long> before,
    Optional<Long> after,
    Optional<Integer> limit,
    Optional<Boolean> withCounts
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        before.ifPresent(val -> body.put("before", val));
        after.ifPresent(val -> body.put("after", val));
        limit.ifPresent(val -> body.put("limit", val));
        withCounts.ifPresent(val -> body.put("with_counts", val));

        return new DiscordRequestBuilder()
            .get()
            .path("/users/@me/guilds")
            .body(body);
    }
}
