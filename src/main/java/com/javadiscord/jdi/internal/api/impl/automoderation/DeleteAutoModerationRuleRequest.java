package com.javadiscord.jdi.internal.api.impl.automoderation;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteAutoModerationRuleRequest(long guildId, long autoModerationRuleId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .delete()
                .path(
                        "/guilds/%s/auto-moderation/rules/%s"
                                .formatted(guildId, autoModerationRuleId));
    }
}
