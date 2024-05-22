package com.javadiscord.jdi.internal.api.audit_logs;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildAuditLogRequest(
    long guildId,
    Optional<Long> userId,
    Optional<Integer> actionType,
    Optional<Long> before,
    Optional<Long> after,
    Optional<Integer> limit,
    Optional<String> reason
) implements DiscordRequest {

    public GetGuildAuditLogRequest {
        if (limit.isPresent() && (limit.get() > 100 || limit.get() < 1)) {
            throw new IllegalArgumentException("limit must be between 1-100");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
            new DiscordRequestBuilder().get().path("/guilds/%s/audit-logs".formatted(guildId));

        userId.ifPresent(val -> discordRequestBuilder.queryParam("user_id", val));
        actionType.ifPresent(val -> discordRequestBuilder.queryParam("action_type", val));
        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
