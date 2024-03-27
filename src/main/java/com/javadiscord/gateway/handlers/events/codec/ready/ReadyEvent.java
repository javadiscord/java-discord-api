package com.javadiscord.gateway.handlers.events.codec.ready;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.gateway.handlers.events.codec.Event;

import java.util.List;

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
        Application application)
        implements Event {}
