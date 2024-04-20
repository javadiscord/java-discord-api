package com.javadiscord.jdi.internal.api.impl.auditlogs;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record GetGuildAuditLogRequest(
        long guildId,
        Optional<Long> userId,
        Optional<Integer> actionType,
        Optional<Long> before,
        Optional<Long> after,
        Optional<Integer> limit)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder().get().path("/guilds/%s/audit-logs".formatted(guildId));

        userId.ifPresent(val -> discordRequestBuilder.queryParam("user_id", val));
        actionType.ifPresent(val -> discordRequestBuilder.queryParam("action_type", val));
        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
