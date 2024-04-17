package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Tags(
        @JsonProperty("bot_id") long botId,
        @JsonProperty("integration_id") long integrationId,
        @JsonProperty("premium_subscriber") boolean premiumSubscriber,
        @JsonProperty("subscription_listing_id") long subscriptionListingId,
        @JsonProperty("available_for_purchase") boolean availableForPurchase,
        @JsonProperty("guild_connections") boolean guildConnections) {}
