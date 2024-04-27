package com.javadiscord.jdi.internal.api.application;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.application.ApplicationInstallParams;

import java.util.List;
import java.util.Map;

public record EditCurrentApplicationRequest(
        String customInstallUrl,
        String description,
        String roleConnectionsVerificationUrl,
        ApplicationInstallParams installParams,
        Map<String, Object> integrationTypesConfig,
        int flags,
        String icon,
        String coverImage,
        String interactionsEndpointUrl,
        List<String> tags)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .patch()
                .path("/applications/@me")
                .body(
                        Map.of(
                                "custom_install_url", customInstallUrl,
                                "description", description,
                                "role_connections_verification_url", roleConnectionsVerificationUrl,
                                "install_params", installParams,
                                "integration_types_config", integrationTypesConfig,
                                "flags", flags,
                                "icon", icon,
                                "cover_image", coverImage,
                                "interactions_endpoint_url", interactionsEndpointUrl,
                                "tags", tags));
    }
}
