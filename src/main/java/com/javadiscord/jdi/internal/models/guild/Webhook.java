package com.javadiscord.jdi.internal.models.guild;

import com.javadiscord.jdi.internal.models.channel.Channel;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Webhook(
    @JsonProperty("id") long id,
    @JsonProperty("type") long type,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("user") User user,
    @JsonProperty("name") String name,
    @JsonProperty("avatar") String avatar,
    @JsonProperty("token") String token,
    @JsonProperty("application_id") long applicationId,
    @JsonProperty("source_guild") Guild sourceGuild,
    @JsonProperty("source_channel") Channel sourceChannel,
    @JsonProperty("url") String url
) {
}
