package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.models.application.ApplicationRoleConnectionMetadata;
import com.javadiscord.jdi.internal.api.application_role_connection_metadata.GetApplicationRoleConnectionMetadataRecords;
import com.javadiscord.jdi.internal.api.application_role_connection_metadata.UpdateApplicationRoleConnectionMetadataRecords;

public class ApplicationRoleConnectionMeta {

    public GetApplicationRoleConnectionMetadataRecords getRecords(long applicationId) {
        return new GetApplicationRoleConnectionMetadataRecords(applicationId);
    }

    public UpdateApplicationRoleConnectionMetadataRecords updateRecords(
            long applicationId,
            ApplicationRoleConnectionMetadata applicationRoleConnectionMetadata) {
        return new UpdateApplicationRoleConnectionMetadataRecords(
                applicationId, applicationRoleConnectionMetadata);
    }
}
