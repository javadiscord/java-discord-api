package com.javadiscord.jdi.core.models.user;

import com.javadiscord.jdi.core.models.guild.Integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Connection(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("type") String type,
    @JsonProperty("revoked") boolean revoked,
    @JsonProperty("integrations") Integration integrations,
    @JsonProperty("verified") boolean verified,
    @JsonProperty("friend_sync") boolean friendSync,
    @JsonProperty("show_activity") boolean showActivity,
    @JsonProperty("two_way_link") boolean twoWayLink,
    @JsonProperty("visibility") int visibility
) {}
