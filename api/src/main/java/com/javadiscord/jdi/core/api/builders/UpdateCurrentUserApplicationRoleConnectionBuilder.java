package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.core.models.application.ApplicationRoleConnectionMetadata;
import com.javadiscord.jdi.internal.api.user.UpdateCurrentUserApplicationRoleConnectionRequest;

public class UpdateCurrentUserApplicationRoleConnectionBuilder {
    private final long applicationId;
    private Optional<String> platformName;
    private Optional<String> platformUsername;
    private Optional<ApplicationRoleConnectionMetadata> metadata;

    public UpdateCurrentUserApplicationRoleConnectionBuilder(long applicationId) {
        this.applicationId = applicationId;
        this.platformName = Optional.empty();
        this.platformUsername = Optional.empty();
        this.metadata = Optional.empty();
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder platformName(String platformName) {
        this.platformName = Optional.of(platformName);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder platformUsername(
        String platformUsername
    ) {
        this.platformUsername = Optional.of(platformUsername);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder metadata(
        ApplicationRoleConnectionMetadata metadata
    ) {
        this.metadata = Optional.of(metadata);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionRequest build() {
        return new UpdateCurrentUserApplicationRoleConnectionRequest(
            applicationId,
            platformName,
            platformUsername,
            metadata
        );
    }
}
