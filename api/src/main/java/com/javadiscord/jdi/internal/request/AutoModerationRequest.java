package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationRule;
import com.javadiscord.jdi.internal.api.auto_moderation.*;
import com.javadiscord.jdi.internal.request.builders.CreateAutoModerationRuleBuilder;
import com.javadiscord.jdi.internal.request.builders.ModifyAutoModerationRuleBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class AutoModerationRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public AutoModerationRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<AutoModerationRule> createAutoModerationRuleRequest(
            CreateAutoModerationRuleBuilder builder) {
        return responseParser.callAndParse(
                AutoModerationRule.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<AutoModerationRule> deleteAutoModerationRule(long autoModerationRuleId) {
        return responseParser.callAndParse(
                AutoModerationRule.class,
                new DeleteAutoModerationRuleRequest(guildId, autoModerationRuleId));
    }

    public AsyncResponse<AutoModerationRule> getAutoModerationRule(long autoModerationRuleId) {
        return responseParser.callAndParse(
                AutoModerationRule.class,
                new GetAutoModerationRuleRequest(guildId, autoModerationRuleId));
    }

    public AsyncResponse<List<AutoModerationRule>> listAutoModerationRules() {
        return responseParser.callAndParseList(
                AutoModerationRule.class, new ListAutoModerationRulesRequest(guildId));
    }

    public AsyncResponse<AutoModerationRule> modifyAutoModerationRule(
            ModifyAutoModerationRuleBuilder builder) {
        return responseParser.callAndParse(
                AutoModerationRule.class, builder.setGuildId(guildId).build());
    }
}
