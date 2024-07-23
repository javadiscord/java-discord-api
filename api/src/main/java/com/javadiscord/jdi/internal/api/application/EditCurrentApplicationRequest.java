package com.javadiscord.jdi.internal.api.application;

import com.javadiscord.jdi.core.models.application.ApplicationInstallParams;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record EditCurrentApplicationRequest(
        Optional<String> customInstallUrl,
        Optional<String> description,
        Optional<String> roleConnectionsVerificationUrl,
        Optional<ApplicationInstallParams> installParams,
        Optional<Map<String, Object>> integrationTypesConfig,
        Optional<Integer> flags,
        Optional<String> icon,
        Optional<String> coverImage,
        Optional<String> interactionsEndpointUrl,
        Optional<List<String>> tags
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();

        customInstallUrl.ifPresent(val -> body.put("custom_install_url", val));
        description.ifPresent(val -> body.put("description", val));
        roleConnectionsVerificationUrl.ifPresent(val -> body.put("role_connections_verification_url", val));
        installParams.ifPresent(val -> body.put("install_params", val));
        integrationTypesConfig.ifPresent(val -> body.put("integration_types_config", val));
        flags.ifPresent(val -> body.put("flags", val));
        icon.ifPresent(val -> body.put("icon", val));
        coverImage.ifPresent(val -> body.put("cover_image", val));
        interactionsEndpointUrl.ifPresent(val -> body.put("interactions_endpoint_url", val));
        tags.ifPresent(val -> body.put("tags", val));

        return new DiscordRequestBuilder()
                .patch()
                .path("/applications/@me")
                .body(body);
    }

}
