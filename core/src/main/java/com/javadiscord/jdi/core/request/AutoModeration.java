package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.CreateAutoModerationRuleBuilder;
import com.javadiscord.jdi.core.request.builders.ModifyAutoModerationRuleBuilder;
import com.javadiscord.jdi.internal.api.auto_moderation.*;

public class AutoModeration {

    public CreateAutoModerationRuleRequest createAutoModerationRuleRequest(
            CreateAutoModerationRuleBuilder builder) {
        return builder.build();
    }

    public DeleteAutoModerationRuleRequest deleteAutoModerationRule(
            long guildId, long autoModerationRuleId) {
        return new DeleteAutoModerationRuleRequest(guildId, autoModerationRuleId);
    }

    public GetAutoModerationRuleRequest getAutoModerationRule(
            long guildId, long autoModerationRuleId) {
        return new GetAutoModerationRuleRequest(guildId, autoModerationRuleId);
    }

    public ListAutoModerationRulesRequest listAutoModerationRules(long guildId) {
        return new ListAutoModerationRulesRequest(guildId);
    }

    public ModifyAutoModerationRuleRequest modifyAutoModerationRule(
            ModifyAutoModerationRuleBuilder builder) {
        return builder.build();
    }
}
