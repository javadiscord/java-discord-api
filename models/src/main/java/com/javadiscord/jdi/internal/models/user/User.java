package com.javadiscord.jdi.internal.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
    @JsonProperty("id") long id,
    @JsonProperty("username") String username,
    @JsonProperty("discriminator") String discriminator,
    @JsonProperty("global_name") String displayName,
    @JsonProperty("avatar") String avatar,
    @JsonProperty("bot") boolean bot,
    @JsonProperty("system") boolean system,
    @JsonProperty("mfa_enabled") boolean mfaEnabled,
    @JsonProperty("banner") String banner,
    @JsonProperty("accent_color") int accentColor,
    @JsonProperty("locale") String locale,
    @JsonProperty("verified") boolean verified,
    @JsonProperty("email") String email,
    @JsonProperty("flags") int flags,
    @JsonProperty("premium_type") int premiumType,
    @JsonProperty("public_flags") int publicFlags,
    @JsonProperty("avtar_decoration") String avatarDecoration
) {}
