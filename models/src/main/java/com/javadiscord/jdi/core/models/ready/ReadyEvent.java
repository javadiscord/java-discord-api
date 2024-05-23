package com.javadiscord.jdi.core.models.ready;

import java.util.List;

import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReadyEvent(
    @JsonProperty("v") int version,
    @JsonProperty("user_settings") UserSetting userSettings,
    User user,
    int[] shard,
    @JsonProperty("session_type") String sessionType,
    @JsonProperty("session_id") String sessionId,
    @JsonProperty("resume_gateway_url") String resumeGatewayURL,
    String[] relationships,
    String[] private_channels,
    String[] presences,
    List<Guild> guilds,
    @JsonProperty("guild_join_requests") String[] guildJoinRequests,
    @JsonProperty("geo_ordered_rtc_regions") String[] geoOrderedRtcRegions,
    Auth auth,
    Application application
) {}
