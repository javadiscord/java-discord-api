package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.ListScheduledEventsRequest;

import java.util.Optional;

public class ListScheduledEventsBuilder {
    private final long guildId;
    private Optional<Boolean> withUserCount;

    public ListScheduledEventsBuilder(long guildId) {
        this.guildId = guildId;
        this.withUserCount = Optional.empty();
    }

    public ListScheduledEventsBuilder withUserCount(boolean withUserCount) {
        this.withUserCount = Optional.of(withUserCount);
        return this;
    }

    public ListScheduledEventsRequest build() {
        return new ListScheduledEventsRequest(guildId, withUserCount);
    }
}
