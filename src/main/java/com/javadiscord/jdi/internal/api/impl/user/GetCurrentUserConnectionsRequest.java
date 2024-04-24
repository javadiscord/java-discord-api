package com.javadiscord.jdi.internal.api.impl.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record GetCurrentUserConnectionsRequest() implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/users/@me/connections");
    }
}
