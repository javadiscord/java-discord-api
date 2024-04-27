package com.javadiscord.jdi.internal.models.guild;

import java.util.List;

import com.javadiscord.jdi.internal.models.channel.Channel;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WidgetObject(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("instant_invite") String instantInvite,
    @JsonProperty("channels") List<Channel> channels,
    @JsonProperty("members") List<User> members,
    @JsonProperty("presence_count") int presenceCount
) {}
