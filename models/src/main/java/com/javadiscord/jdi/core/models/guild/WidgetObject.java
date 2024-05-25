package com.javadiscord.jdi.core.models.guild;

import java.util.List;

import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.user.User;

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
