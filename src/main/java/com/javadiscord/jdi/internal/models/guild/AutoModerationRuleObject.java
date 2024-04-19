package com.javadiscord.jdi.internal.models.guild;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationRuleObject(
    @JsonProperty("id") long id,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("name") String name,
    @JsonProperty("creator_id") long creatorId,
    @JsonProperty("event_type") int eventType,
    @JsonProperty("trigger_type") int triggerType,
    @JsonProperty("trigger_metadata") AutoModerationTriggerMetadata triggerMetadata,
    @JsonProperty("actions") List<AutoModerationActionObject> actions,
    @JsonProperty("enabled") boolean enabled,
    @JsonProperty("exempt_roles") List<Long> exemptRoles,
    @JsonProperty("exempt_channels") List<Long> exemptChannels
) {}
