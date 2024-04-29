package com.javadiscord.jdi.internal.api.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.List;
import java.util.Map;

public record CreateGroupDMRequest(List<String> accessTokens, Map<Long, String> nicks)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/users/@me/channels")
                .body(
                        Map.of(
                                "access_tokens", accessTokens,
                                "nicks", nicks));
    }
}
