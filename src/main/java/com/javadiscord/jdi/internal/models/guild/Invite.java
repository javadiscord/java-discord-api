package com.javadiscord.jdi.internal.models.guild;

import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Invite(
    @JsonProperty("channel_id") String channelId,
    @JsonProperty("code") String code,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("guild_id") String guildId,
    @JsonProperty("inviter") User inviter,
    @JsonProperty("max_age") int maxAge,
    @JsonProperty("max_uses") int maxUses,
    @JsonProperty("target_type") Integer targetType,
    @JsonProperty("target_user") User targetUser,
    @JsonProperty("target_application") Application targetApplication,
    @JsonProperty("temporary") boolean temporary,
    @JsonProperty("uses") int uses
) {}
