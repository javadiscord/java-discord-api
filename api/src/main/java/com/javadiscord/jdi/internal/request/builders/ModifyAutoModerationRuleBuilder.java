package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationAction;
import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationTriggerMetadata;
import com.javadiscord.jdi.internal.api.auto_moderation.ModifyAutoModerationRuleRequest;

import java.util.List;
import java.util.Optional;

public class ModifyAutoModerationRuleBuilder {
    private long guildId;
    private final long autoModerationRuleId;
    private Optional<String> name;
    private Optional<Integer> eventType;
    private Optional<AutoModerationTriggerMetadata> triggerMetadata;
    private Optional<List<AutoModerationAction>> actions;
    private Optional<Boolean> enabled;
    private Optional<List<Long>> exemptRoles;
    private Optional<List<Long>> exemptChannel;

    public ModifyAutoModerationRuleBuilder(long autoModerationRuleId) {
        this.autoModerationRuleId = autoModerationRuleId;
        this.name = Optional.empty();
        this.eventType = Optional.empty();
        this.triggerMetadata = Optional.empty();
        this.actions = Optional.empty();
        this.enabled = Optional.empty();
        this.exemptRoles = Optional.empty();
        this.exemptChannel = Optional.empty();
    }

    public ModifyAutoModerationRuleBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyAutoModerationRuleBuilder eventType(Integer eventType) {
        this.eventType = Optional.of(eventType);
        return this;
    }

    public ModifyAutoModerationRuleBuilder triggerMetadata(
            AutoModerationTriggerMetadata triggerMetadata) {
        this.triggerMetadata = Optional.of(triggerMetadata);
        return this;
    }

    public ModifyAutoModerationRuleBuilder actions(List<AutoModerationAction> actions) {
        this.actions = Optional.of(actions);
        return this;
    }

    public ModifyAutoModerationRuleBuilder enabled(Boolean enabled) {
        this.enabled = Optional.of(enabled);
        return this;
    }

    public ModifyAutoModerationRuleBuilder exemptRoles(List<Long> exemptRoles) {
        this.exemptRoles = Optional.of(exemptRoles);
        return this;
    }

    public ModifyAutoModerationRuleBuilder exemptChannel(List<Long> exemptChannel) {
        this.exemptChannel = Optional.of(exemptChannel);
        return this;
    }

    public ModifyAutoModerationRuleRequest build() {
        return new ModifyAutoModerationRuleRequest(
                guildId,
                autoModerationRuleId,
                name,
                eventType,
                triggerMetadata,
                actions,
                enabled,
                exemptRoles,
                exemptChannel);
    }

    public ModifyAutoModerationRuleBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
