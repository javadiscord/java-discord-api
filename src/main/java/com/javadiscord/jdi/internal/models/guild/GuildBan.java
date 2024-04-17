package com.javadiscord.jdi.internal.models.guild;

import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GuildBan(@JsonProperty("guild_id") String guildId, @JsonProperty("user") User user) {
}
