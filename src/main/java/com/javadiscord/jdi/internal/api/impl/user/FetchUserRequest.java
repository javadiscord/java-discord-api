package com.javadiscord.jdi.internal.api.impl.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record FetchUserRequest(String userId) implements DiscordRequest {
	@Override
	public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .url("/users/" + userId())
            .get();
	}
}
