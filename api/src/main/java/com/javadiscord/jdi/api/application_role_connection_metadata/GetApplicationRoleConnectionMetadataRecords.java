package com.javadiscord.jdi.api.application_role_connection_metadata;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetApplicationRoleConnectionMetadataRecords(long applicationId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .path("/applications/%s/role-connections/metadata".formatted(applicationId))
            .get();
    }
}
