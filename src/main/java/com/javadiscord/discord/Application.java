package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.discord.user.User;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Application(
        String id,
        String name,
        String icon,
        String description,
        List<String> rpcOrigins,
        @JsonProperty("bot_public") boolean botPublic,
        @JsonProperty("bot_require_code_grant") boolean botRequireCodeGrant,
        User bot,
        @JsonProperty("terms_of_service_url") String termsOfServiceUrl,
        @JsonProperty("privacy_policy_url") String privacyPolicyUrl,
        User owner,
        String verifyKey,
        @JsonProperty("guild_id") String guildId,
        Guild guild,
        @JsonProperty("primary_sku_id") String primarySkuId,
        String slug,
        @JsonProperty("cover_image") String coverImage,
        int flags,
        @JsonProperty("approximate_guild_count") int approximateGuildCount,
        @JsonProperty("redirect_uris") List<String> redirectUris,
        @JsonProperty("interactions_endpoint_url") String interactionsEndpointUrl,
        @JsonProperty("role_connections_verification_url") String roleConnectionsVerificationUrl,
        List<String> tags,
        @JsonProperty("custom_install_url") String customInstallUrl) {}
