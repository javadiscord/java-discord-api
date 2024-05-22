package com.javadiscord.jdi.core.models.guild;

import java.time.OffsetDateTime;
import java.util.List;

import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Member(
    @JsonProperty("user") User user,
    @JsonProperty("nick") String nick,
    @JsonProperty("avatar") String avatar,
    @JsonProperty("roles") List<Long> roles,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "joined_at"
    ) OffsetDateTime joinedAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "premium_since"
    ) OffsetDateTime premiumSince,
    @JsonProperty("deaf") boolean deaf,
    @JsonProperty("mute") boolean mute,
    @JsonProperty("flags") int flags,
    @JsonProperty("pending") boolean pending,
    @JsonProperty("permissions") String permissions,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "communication_disabled_until"
    ) OffsetDateTime communicationDisabledUntil
) {}
