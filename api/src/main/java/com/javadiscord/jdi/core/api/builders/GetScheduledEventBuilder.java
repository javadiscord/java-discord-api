package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.GetScheduledEventRequest;

public class GetScheduledEventBuilder {
    private long guildId;
    private final long scheduledEventId;
    private Optional<Boolean> withUserCount;

    public GetScheduledEventBuilder(long scheduledEventId) {
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

    public GetScheduledEventBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
