package com.javadiscord.jdi.internal.api.auto_moderation;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ListAutoModerationRulesRequest(long guildId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .get()
                .path("/guilds/%s/auto-moderation/rules".formatted(guildId));
    }
}
