package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.GetScheduledEventRequest;

import java.util.Optional;

public class GetScheduledEventBuilder {
    private final long guildId;
    private final long scheduledEventId;
    private Optional<Boolean> withUserCount;

    public GetScheduledEventBuilder(long guildId, long scheduledEventId) {
        this.guildId = guildId;
        this.scheduledEventId = scheduledEventId;
        this.withUserCount = Optional.empty();
    }

    public GetScheduledEventBuilder withUserCount(boolean withUserCount) {
        this.withUserCount = Optional.of(withUserCount);
        return this;
    }

    public GetScheduledEventRequest build() {
        return new GetScheduledEventRequest(guildId, scheduledEventId, withUserCount);
    }
}
