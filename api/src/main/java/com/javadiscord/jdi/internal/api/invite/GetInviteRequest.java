package com.javadiscord.jdi.internal.api.invite;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record GetInviteRequest(
        String inviteCode,
        Optional<Boolean> withCounts,
        Optional<Boolean> withExpiration,
        Optional<Long> guildScheduledEventId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        withCounts.ifPresent(val -> body.put("with_counts", val));
        withExpiration.ifPresent(val -> body.put("with_expiration", val));
        guildScheduledEventId.ifPresent(val -> body.put("guild_scheduled_event_id", val));

        return new DiscordRequestBuilder()
                .get()
                .path("/invites/%s".formatted(inviteCode))
                .body(body);
    }
}
