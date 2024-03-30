package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.Member;
import com.javadiscord.jdi.internal.models.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EventUser(
        @JsonProperty("guild_scheduled_event_id") long guildScheduledEventId,
        User user,
        Member member) {}
