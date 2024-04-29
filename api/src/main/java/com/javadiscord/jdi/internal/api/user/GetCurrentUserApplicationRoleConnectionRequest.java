package com.javadiscord.jdi.internal.api.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetCurrentUserApplicationRoleConnectionRequest(long applicationId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .get()
                .path("/users/@me/applications/%d/role-connection".formatted(applicationId));
    }
}
