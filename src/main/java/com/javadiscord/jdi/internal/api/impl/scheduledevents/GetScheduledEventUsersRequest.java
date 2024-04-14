package com.javadiscord.jdi.internal.api.impl.scheduledevents;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record GetScheduledEventUsersRequest(
        long guildId,
        long scheduledEventId,
        Optional<Integer> limit,
        Optional<Boolean> withMember,
        Optional<Long> before, // both of these can be provided but before will be favored
        Optional<Long> after)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        limit.ifPresent(val -> body.put("limit", val));
        withMember.ifPresent(val -> body.put("with_member", val));
        before.ifPresent(val -> body.put("before", val));
        after.ifPresent(val -> body.put("after", val));

        return new DiscordRequestBuilder()
                .get()
                .path("/guilds/%s/scheduled-events/%s/users".formatted(guildId, scheduledEventId))
                .body(body);
    }
}
