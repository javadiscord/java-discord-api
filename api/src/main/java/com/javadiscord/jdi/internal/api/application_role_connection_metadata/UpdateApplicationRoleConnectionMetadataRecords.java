package com.javadiscord.jdi.internal.api.application_role_connection_metadata;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.application.ApplicationRoleConnectionMetadata;

public record UpdateApplicationRoleConnectionMetadataRecords(
        long applicationId, ApplicationRoleConnectionMetadata applicationRoleConnectionMetadata)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .path("/applications/%s/role-connections/metadata".formatted(applicationId))
                .put()
                .body(applicationRoleConnectionMetadata);
    }
}
