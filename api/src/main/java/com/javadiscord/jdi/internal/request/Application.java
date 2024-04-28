package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.application.ApplicationInstallParams;
import com.javadiscord.jdi.internal.api.application.EditCurrentApplicationRequest;
import com.javadiscord.jdi.internal.api.application.GetCurrentApplicationRequest;

import java.util.List;
import java.util.Map;

public class Application {

    public EditCurrentApplicationRequest editCurrentApplication(
            String customInstallUrl,
            String description,
            String roleConnectionsVerificationUrl,
            ApplicationInstallParams installParams,
            Map<String, Object> integrationTypesConfig,
            int flags,
            String icon,
            String coverImage,
            String interactionsEndpointUrl,
            List<String> tags) {
        return new EditCurrentApplicationRequest(
                customInstallUrl,
                description,
                roleConnectionsVerificationUrl,
                installParams,
                integrationTypesConfig,
                flags,
                icon,
                coverImage,
                interactionsEndpointUrl,
                tags);
    }

    public GetCurrentApplicationRequest getCurrentApplication() {
        return new GetCurrentApplicationRequest();
    }
}
