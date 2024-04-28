package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.core.models.application.ApplicationRoleConnectionMetadata;
import com.javadiscord.jdi.internal.api.user.UpdateCurrentUserApplicationRoleConnectionRequest;

import java.util.Optional;

public class UpdateCurrentUserApplicationRoleConnectionBuilder {
    private final long applicationId;
    private Optional<String> platformName;
    private Optional<String> platformUsername;
    private Optional<ApplicationRoleConnectionMetadata> metadata;

    public UpdateCurrentUserApplicationRoleConnectionBuilder(long applicationId) {
        this.applicationId = applicationId;
        platformName = Optional.empty();
        platformUsername = Optional.empty();
        metadata = Optional.empty();
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder setPlatformName(String platformName) {
        this.platformName = Optional.of(platformName);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder setPlatformUsername(
            String platformUsername) {
        this.platformUsername = Optional.of(platformUsername);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionBuilder setMetadata(
            ApplicationRoleConnectionMetadata metadata) {
        this.metadata = Optional.of(metadata);
        return this;
    }

    public UpdateCurrentUserApplicationRoleConnectionRequest build() {
        return new UpdateCurrentUserApplicationRoleConnectionRequest(
                applicationId, platformName, platformUsername, metadata);
    }
}
