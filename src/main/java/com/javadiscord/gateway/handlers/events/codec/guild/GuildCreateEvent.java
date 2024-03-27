package com.javadiscord.gateway.handlers.events.codec.guild;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.gateway.handlers.events.codec.Event;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuildCreateEvent implements Event {
    @JsonProperty("large")
    private boolean large;

    @JsonProperty("embedded_activities")
    private String[] embeddedActivities;

    @JsonProperty("vanity_url_code")
    private String vanityUrlCode;

    @JsonProperty("member_count")
    private int memberCount;

    @JsonProperty("max_members")
    private int maxMembers;

    @JsonProperty("premium_subscription_count")
    private int premiumSubscriptionCount;

    @JsonProperty("nsfw")
    private boolean nsfw;

    @JsonProperty("rules_channel_id")
    private long rulesChannelId;

    @JsonProperty("joined_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    private Date joinDate;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("unavailable")
    private boolean unavailable;

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("banner")
    private String banner;
}
