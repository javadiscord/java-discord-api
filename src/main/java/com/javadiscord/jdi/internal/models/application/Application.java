package com.javadiscord.jdi.internal.models.application;

import java.util.List;

import com.javadiscord.jdi.internal.models.guild.Guild;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * https://discord.com/developers/docs/resources/application#application-object-application-structure
 * Missing:
 * team
 * integration types config
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Application(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("icon") String icon,
    @JsonProperty("description") String description,
    @JsonProperty("rpc_origins") List<String> rpcOrigins,
    @JsonProperty("bot_public") boolean botPublic,
    @JsonProperty("bot_require_code_grant") boolean botRequireCodeGrant,
    @JsonProperty("bot") User bot,
    @JsonProperty("terms_of_service_url") String termsOfServiceUrl,
    @JsonProperty("privacy_policy_url") String privacyPolicyUrl,
    @JsonProperty("owner") User owner,
    @JsonProperty("verify_key") String verifyKey,
    @JsonProperty("guild_id") String guildId,
    @JsonProperty("guild") Guild guild,
    @JsonProperty("primary_sku_id") String primarySkuId,
    @JsonProperty("slug") String slug,
    @JsonProperty("cover_image") String coverImage,
    @JsonProperty("flags") int flags,
    @JsonProperty("approximate_guild_count") int approximateGuildCount,
    @JsonProperty("redirect_uris") List<String> redirectUris,
    @JsonProperty("interactions_endpoint_url") String interactionsEndpointUrl,
    @JsonProperty("role_connections_verification_url") String roleConnectionsVerificationUrl,
    @JsonProperty("tags") List<String> tags,
    @JsonProperty("install_params") ApplicationInstallParams installParams,
    @JsonProperty("custom_install_url") String customInstallUrl
) {}
