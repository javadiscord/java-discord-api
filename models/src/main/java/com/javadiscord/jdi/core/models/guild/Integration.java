package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.user.User;

import java.time.OffsetDateTime;

// TODO: finish implementation
// https://discord.com/developers/docs/resources/guild#integration-object
@JsonIgnoreProperties(ignoreUnknown = true)
public record Integration(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("type") String type,
        @JsonProperty("enabled") boolean enabled,
        @JsonProperty("syncing") boolean syncing,
        @JsonProperty("role_id") long roleId,
        @JsonProperty("enable_emoticons") boolean enableEmoticons,
        @JsonProperty("expire_behaviour") int expireBehaviour,
        @JsonProperty("expire_grace_period") int expireGracePeriod,
        @JsonProperty("user") User user,
        @JsonProperty("account") IntegrationAccount account,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
                @JsonProperty("synced_at")
                OffsetDateTime syncedAt,
        @JsonProperty("subscriber_count") int subscriberCount,
        @JsonProperty("revoked") boolean revoked,
        @JsonProperty("application") IntegrationApplication application) {}
