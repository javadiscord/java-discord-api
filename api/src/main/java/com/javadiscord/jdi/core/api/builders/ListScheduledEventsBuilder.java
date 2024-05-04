package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.ListScheduledEventsRequest;

import java.util.Optional;

public class ListScheduledEventsBuilder {
    private long guildId;
    private Optional<Boolean> withUserCount;

    public ListScheduledEventsBuilder() {
        this.withUserCount = Optional.empty();
    }

    public ListScheduledEventsBuilder withUserCount(boolean withUserCount) {
        this.withUserCount = Optional.of(withUserCount);
        return this;
    }

    public ListScheduledEventsRequest build() {
        return new ListScheduledEventsRequest(guildId, withUserCount);
    }

    public ListScheduledEventsBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
