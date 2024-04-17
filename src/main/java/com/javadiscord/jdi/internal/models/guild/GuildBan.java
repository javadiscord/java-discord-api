package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.user.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GuildBan(@JsonProperty("guild_id") String guildId, @JsonProperty("user") User user) {}
