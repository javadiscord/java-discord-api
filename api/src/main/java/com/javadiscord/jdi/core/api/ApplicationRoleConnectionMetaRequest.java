package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.models.application.ApplicationRoleConnectionMetadata;
import com.javadiscord.jdi.internal.api.application_role_connection_metadata.GetApplicationRoleConnectionMetadataRecords;
import com.javadiscord.jdi.internal.api.application_role_connection_metadata.UpdateApplicationRoleConnectionMetadataRecords;

public class ApplicationRoleConnectionMetaRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public ApplicationRoleConnectionMetaRequest(
        DiscordResponseParser responseParser, long guildId
    ) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<ApplicationRoleConnectionMetadata> getRecords(long applicationId) {
        return responseParser.callAndParse(
            ApplicationRoleConnectionMetadata.class,
            new GetApplicationRoleConnectionMetadataRecords(applicationId)
        );
    }

    public AsyncResponse<ApplicationRoleConnectionMetadata> updateRecords(
        long applicationId,
        ApplicationRoleConnectionMetadata applicationRoleConnectionMetadata
    ) {
        return responseParser.callAndParse(
            ApplicationRoleConnectionMetadata.class,
            new UpdateApplicationRoleConnectionMetadataRecords(
                applicationId,
                applicationRoleConnectionMetadata
            )
        );
    }
}
