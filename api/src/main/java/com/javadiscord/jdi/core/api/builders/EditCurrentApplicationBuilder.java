package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.application.ApplicationInstallParams;
import com.javadiscord.jdi.internal.api.application.EditCurrentApplicationRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EditCurrentApplicationBuilder {

    private Optional<String> customInstallUrl = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> roleConnectionsVerificationUrl = Optional.empty();
    private Optional<ApplicationInstallParams> installParams = Optional.empty();
    private Optional<Map<String, Object>> integrationTypesConfig = Optional.empty();
    private Optional<Integer> flags = Optional.empty();
    private Optional<String> icon = Optional.empty();
    private Optional<String> coverImage = Optional.empty();
    private Optional<String> interactionsEndpointUrl = Optional.empty();
    private Optional<List<String>> tags = Optional.empty();

    public EditCurrentApplicationBuilder customInstallUrl(String customInstallUrl) {
        this.customInstallUrl = Optional.of(customInstallUrl);
        return this;
    }

    public EditCurrentApplicationBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public EditCurrentApplicationBuilder roleConnectionsVerificationUrl(String roleConnectionsVerificationUrl) {
        this.roleConnectionsVerificationUrl = Optional.of(roleConnectionsVerificationUrl);
        return this;
    }

    public EditCurrentApplicationBuilder installParams(ApplicationInstallParams installParams) {
        this.installParams = Optional.of(installParams);
        return this;
    }

    public EditCurrentApplicationBuilder integrationTypesConfig(Map<String, Object> integrationTypesConfig) {
        this.integrationTypesConfig = Optional.of(integrationTypesConfig);
        return this;
    }

    public EditCurrentApplicationBuilder flags(int flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public EditCurrentApplicationBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public EditCurrentApplicationBuilder coverImage(String coverImage) {
        this.coverImage = Optional.of(coverImage);
        return this;
    }

    public EditCurrentApplicationBuilder interactionsEndpointUrl(String interactionsEndpointUrl) {
        this.interactionsEndpointUrl = Optional.of(interactionsEndpointUrl);
        return this;
    }

    public EditCurrentApplicationBuilder tags(List<String> tags) {
        this.tags = Optional.of(tags);
        return this;
    }

    public EditCurrentApplicationRequest build() {
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
                tags
        );
    }

}
