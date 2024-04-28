package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationAction;
import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationTriggerMetadata;
import com.javadiscord.jdi.internal.api.auto_moderation.CreateAutoModerationRuleRequest;

import java.util.List;
import java.util.Optional;

public class CreateAutoModerationRuleBuilder {
    private final long guildId;
    private final String name;
    private final int eventType;
    private final int triggerType;
    private final List<AutoModerationAction> actions;
    private Optional<AutoModerationTriggerMetadata> triggerMetadata;
    private Optional<Boolean> enabled;
    private Optional<List<Long>> exemptRoles;
    private Optional<List<Long>> exemptChannels;

    public CreateAutoModerationRuleBuilder(
            long guildId,
            String name,
            int eventType,
            int triggerType,
            List<AutoModerationAction> actions) {
        this.guildId = guildId;
        this.name = name;
        this.eventType = eventType;
        this.triggerType = triggerType;
        this.actions = actions;
        triggerMetadata = Optional.empty();
        enabled = Optional.empty();
        exemptRoles = Optional.empty();
        exemptChannels = Optional.empty();
    }

    public CreateAutoModerationRuleBuilder enabled(boolean enabled) {
        this.enabled = Optional.of(enabled);
        return this;
    }

    public CreateAutoModerationRuleBuilder exemptRoles(List<Long> exemptRoles) {
        this.exemptRoles = Optional.of(exemptRoles);
        return this;
    }

    public CreateAutoModerationRuleBuilder exemptChannels(List<Long> exemptChannels) {
        this.exemptChannels = Optional.of(exemptChannels);
        return this;
    }

    public CreateAutoModerationRuleRequest build() {
        return new CreateAutoModerationRuleRequest(
                guildId,
                name,
                eventType,
                triggerType,
                actions,
                triggerMetadata,
                enabled,
                exemptRoles,
                exemptChannels);
    }
}
