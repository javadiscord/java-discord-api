package com.javadiscord.jdi.internal.api.auto_moderation;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationAction;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationTriggerMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ModifyAutoModerationRuleRequest(
        long guildId,
        long autoModerationRuleId,
        Optional<String> name,
        Optional<Integer> eventType,
        Optional<AutoModerationTriggerMetadata> triggerMetadata,
        Optional<List<AutoModerationAction>> actions,
        Optional<Boolean> enabled,
        Optional<List<Long>> exemptRoles,
        Optional<List<Long>> exemptChannels)
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

        return new DiscordRequestBuilder()
                .patch()
                .path(
                        "/guilds/%s/auto-moderation/rules/%s"
                                .formatted(guildId, autoModerationRuleId))
                .body(body);
    }
}
