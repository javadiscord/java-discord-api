package com.javadiscord.jdi.api.user;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetCurrentUserApplicationRoleConnectionRequest(long applicationId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/users/@me/applications/%d/role-connection".formatted(applicationId));
    }
}
