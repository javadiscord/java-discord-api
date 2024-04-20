package com.javadiscord.jdi.internal.api.impl.user;

import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.application.ApplicationRoleConnectionMetadata;

public final record UpdateCurrentUserApplicationRoleConnectionRequest(
    long applicationId,
    String platformName,
    String platformUsername,
    ApplicationRoleConnectionMetadata metadata
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .path("/users/@me/applications/%d/role-connection".formatted(applicationId))
            .put()
            .body(
                Map.of(
                    "platform_name", platformName,
                    "platform_username", platformUsername,
                    "metadata", metadata
                )
            );
    }
}
