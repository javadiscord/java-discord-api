package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Entitlement(
        @JsonProperty("id") long id,
        @JsonProperty("sku_id") long skuId,
        @JsonProperty("application_id") long applicationId,
        @JsonProperty("user_id") long userId,
        @JsonProperty("type") int type,
        @JsonProperty("deleted") boolean deleted,
        @JsonProperty("starts_at") String startsAt,
        @JsonProperty("ends_at") String endsAt,
        @JsonProperty("guild_id") long guildId) {}
