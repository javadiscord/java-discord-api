package com.javadiscord.jdi.api.auto_moderation;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetAutoModerationRuleRequest(long guildId, long autoModerationRuleId)
    implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path(
                "/guilds/%s/auto-moderation/rules/%s"
                    .formatted(guildId, autoModerationRuleId)
            );
    }
}
