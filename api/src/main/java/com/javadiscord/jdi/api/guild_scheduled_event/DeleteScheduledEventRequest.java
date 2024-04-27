package com.javadiscord.jdi.api.guild_scheduled_event;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record DeleteScheduledEventRequest(long guildId, long scheduledEventId)
    implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/guilds/%s/scheduled-events/%s".formatted(guildId, scheduledEventId));
    }
}
