package com.javadiscord.jdi.core.api;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.models.application.Application;
import com.javadiscord.jdi.core.models.application.ApplicationInstallParams;
import com.javadiscord.jdi.internal.api.application.EditCurrentApplicationRequest;
import com.javadiscord.jdi.internal.api.application.GetCurrentApplicationRequest;

public class ApplicationRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public ApplicationRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Application> editCurrentApplication(
        String customInstallUrl,
        String description,
        String roleConnectionsVerificationUrl,
        ApplicationInstallParams installParams,
        Map<String, Object> integrationTypesConfig,
        int flags,
        String icon,
        String coverImage,
        String interactionsEndpointUrl,
        List<String> tags
    ) {
        return responseParser.callAndParse(
            Application.class,
            new EditCurrentApplicationRequest(
                customInstallUrl,
                description,
                roleConnectionsVerificationUrl,
                installParams,
                integrationTypesConfig,
                flags,
                icon,
                coverImage,
                interactionsEndpointUrl,
                tags
            )
        );
    }

    public AsyncResponse<Application> getCurrentApplication() {
        return responseParser.callAndParse(Application.class, new GetCurrentApplicationRequest());
    }
}
