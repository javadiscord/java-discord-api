package com.javadiscord.jdi.internal.api.guild_scheduled_event;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetScheduledEventUsersRequest(
    long guildId,
    long scheduledEventId,
    Optional<Integer> limit,
    Optional<Boolean> withMember,
    Optional<Long> before,
    Optional<Long> after
) implements DiscordRequest {

    public GetScheduledEventUsersRequest {
        if (before.isPresent() && after.isPresent()) {
            after = Optional.empty();
        }

        if (limit.isPresent() && limit.get() > 100) {
            throw new IllegalArgumentException("limit must be less than 100");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .get()
                .path(
                    "/guilds/%s/scheduled-events/%s/users"
                        .formatted(guildId, scheduledEventId)
                );

        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));
        withMember.ifPresent(val -> discordRequestBuilder.queryParam("with_member", val));
        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));

        return discordRequestBuilder;
    }
}
