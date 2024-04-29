package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RoleSubscriptionData(
        @JsonProperty("role_subscription_listing_id") long roleSubscriptionListingId,
        @JsonProperty("tier_name") String tierName,
        @JsonProperty("total_months_subscribed") int totalMonthsSubscribed,
        @JsonProperty("is_renewal") boolean isRenewal) {}
