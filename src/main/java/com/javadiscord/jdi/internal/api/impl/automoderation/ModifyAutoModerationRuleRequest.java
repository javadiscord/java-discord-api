package com.javadiscord.jdi.internal.api.impl.automoderation;

import java.util.*;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.AutoModerationActionObject;
import com.javadiscord.jdi.internal.models.guild.AutoModerationTriggerMetadata;

public record ModifyAutoModerationRuleRequest(long guildId, long autoModerationRuleId,
        Optional<String> name, Optional<Integer> eventType,
        Optional<AutoModerationTriggerMetadata> triggerMetadata,
        Optional<List<AutoModerationActionObject>> actions, Optional<Boolean> enabled,
        Optional<List<Long>> exemptRoles, Optional<List<Long>> exemptChannels)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        eventType.ifPresent(val -> body.put("event_type", val));
        triggerMetadata.ifPresent(val -> body.put("trigger_metadata", val));
        actions.ifPresent(val -> body.put("actions", val));
        enabled.ifPresent(val -> body.put("enabled", val));
        exemptRoles.ifPresent(val -> body.put("exempt_roles", exemptRoles));
        exemptChannels.ifPresent(val -> body.put("exempt_channels", exemptChannels));

        return new DiscordRequestBuilder().patch()
            .path("/guilds/%s/auto-moderation/rules/%s".formatted(guildId, autoModerationRuleId))
            .body(body);
    }
}
