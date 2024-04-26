package com.javadiscord.jdi.internal.api.auto_moderation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationAction;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationTriggerMetadata;

public record CreateAutoModerationRuleRequest(
    long guildId,
    String name,
    int eventType,
    int triggerType,
    Optional<AutoModerationTriggerMetadata> triggerMetadata,
    List<AutoModerationAction> actions,
    Optional<Boolean> enabled,
    Optional<List<Long>> exemptRoles,
    Optional<List<Long>> exemptChannels
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("event_type", eventType);
        body.put("trigger_type", triggerType);
        triggerMetadata.ifPresent(val -> body.put("trigger_metadata", val));
        body.put("actions", actions);
        enabled.ifPresent(val -> body.put("enabled", val));
        exemptRoles.ifPresent(val -> body.put("exempt_roles", val));
        exemptChannels.ifPresent(val -> body.put("exempt_channels", val));

        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/auto-moderation/rules".formatted(guildId))
            .body(body);
    }
}
