package com.javadiscord.jdi.internal.models.invite;

import com.javadiscord.jdi.internal.models.application.Application;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Invite(
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("code") String code,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("inviter") User inviter,
    @JsonProperty("max_age") int maxAge,
    @JsonProperty("max_uses") int maxUses,
    @JsonProperty("target_type") InviteTargetType targetType,
    @JsonProperty("target_user") User targetUser,
    @JsonProperty("target_application") Application targetApplication,
    @JsonProperty("temporary") boolean temporary,
    @JsonProperty("uses") int uses
) {}
