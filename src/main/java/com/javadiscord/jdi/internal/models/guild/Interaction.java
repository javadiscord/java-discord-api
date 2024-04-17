package com.javadiscord.jdi.internal.models.guild;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.internal.models.channel.Channel;
import com.javadiscord.jdi.internal.models.message.Message;
import com.javadiscord.jdi.internal.models.user.Member;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Interaction(
    @JsonProperty("id") long id,
    @JsonProperty("application_id") long applicationId,
    @JsonProperty("type") String type,
    @JsonProperty("data") InteractionData data,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("channel") Channel channel,
    @JsonProperty("channel_id") long channelId,
    @JsonProperty("member") Member member,
    @JsonProperty("user") User user,
    @JsonProperty("token") String token,
    @JsonProperty("version") int version,
    @JsonProperty("message") Message message,
    @JsonProperty("app_permissions") String appPermissions,
    @JsonProperty("locale") String locale,
    @JsonProperty("guild_locale") String guildLocale,
    @JsonProperty("entitlements") List<Entitlement> entitlements,
    @JsonAlias("authorizing_integration_owners") Map<String, Object> authorizingIntegrationOwners,
    @JsonProperty("context") String context
) {
}
