package com.javadiscord.jdi.internal.api.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.models.application.ApplicationRoleConnectionMetadata;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record UpdateCurrentUserApplicationRoleConnectionRequest(
    Long applicationId,
    Optional<String> platformName,
    Optional<String> platformUsername,
    Optional<ApplicationRoleConnectionMetadata> metadata
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> payload = new HashMap<>();
        platformName.ifPresent(val -> payload.put("platform_name", platformName));
        platformUsername.ifPresent(val -> payload.put("platform_username", platformUsername));
        metadata.ifPresent(val -> payload.put("metadata", metadata));

        return new DiscordRequestBuilder()
            .path("/users/@me/applications/%d/role-connection".formatted(applicationId))
            .put()
            .body(payload);
    }
}
