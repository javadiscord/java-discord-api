package com.javadiscord.jdi.internal.api.impl.user;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record CreateGroupDMRequest(List<String> accessTokens, Map<Long, String> nicks) implements DiscordRequest {
	@Override
	public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/users/@me/channels")
            .body(Map.of(
                "access_tokens", accessTokens,
                "nicks", nicks
            ));
	}
}
