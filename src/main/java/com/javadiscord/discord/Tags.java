package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tags {
    @JsonProperty("bot_id")
    private long botId;

    @JsonProperty("integration_id")
    private long integrationId;

    @JsonProperty("premium_subscriber")
    private boolean premiumSubscriber;

    @JsonProperty("subscription_listing_id")
    private long subscriptionListingId;

    @JsonProperty("available_for_purchase")
    private boolean availableForPurchase;

    @JsonProperty("guild_connections")
    private boolean guildConnections;
}
