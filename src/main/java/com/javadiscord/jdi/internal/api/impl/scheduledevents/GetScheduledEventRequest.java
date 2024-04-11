package com.javadiscord.jdi.internal.api.impl.scheduledevents;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record GetScheduledEventRequest(
        String guildId, String scheduledEventId, Optional<Boolean> withUserCount)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        withUserCount.ifPresent(val -> body.put("with_user_count", val));
        return new DiscordRequestBuilder()
                .get()
                .path("/guilds/%s/scheduled-events/%s".formatted(guildId, scheduledEventId))
                .body(body);
    }
}
