package com.javadiscord.jdi.core.models.scheduled_event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EventUser(
        @JsonProperty("guild_scheduled_event_id") long guildScheduledEventId,
        User user,
        Member member) {}
