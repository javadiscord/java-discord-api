package com.javadiscord.jdi.internal.api.impl.application;

import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record EditCurrentApplicationRequest(
    String customInstallUrl,
    String description,
    String roleConnectionsVerificationUrl,
    Object installParams, // TODO: Create Install Params object
    Map<String, Object> integrationTypesConfig,
    int flags,
    String icon, // Image data, set as String for now
    String coverImage, // Image data, set as String for now
    String interactionsEndpointUrl,
    String[] tags
)
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
                    "tags", tags
                )
            );
    }
}
