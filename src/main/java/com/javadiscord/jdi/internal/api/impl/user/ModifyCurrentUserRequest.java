package com.javadiscord.jdi.internal.api.impl.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record ModifyCurrentUserRequest() implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return null;
        // TODO: https://discord.com/developers/docs/resources/user#modify-current-user
    }
}
