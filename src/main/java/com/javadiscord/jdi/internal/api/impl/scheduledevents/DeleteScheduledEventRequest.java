package com.javadiscord.jdi.internal.api.impl.scheduledevents;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteScheduledEventRequest(long guildId, long scheduledEventId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .delete()
                .path("/guilds/%s/scheduled-events/%s".formatted(guildId, scheduledEventId));
    }
}
