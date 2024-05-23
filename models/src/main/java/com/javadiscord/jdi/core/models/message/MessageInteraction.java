package com.javadiscord.jdi.core.models.message;

import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageInteraction(
    @JsonProperty("id") long id,
    @JsonProperty("type") int type,
    @JsonProperty("name") String name,
    @JsonProperty("user") User user,
    @JsonProperty("member") Member member
) {}
