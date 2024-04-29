package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.GetScheduledEventUsersRequest;

import java.util.Optional;

public class GetScheduledEventUsersBuilder {
    private long guildId;
    private final long scheduledEventId;
    private Optional<Integer> limit;
    private Optional<Boolean> withMember;
    private Optional<Long> before;
    private Optional<Long> after;

    public GetScheduledEventUsersBuilder(long scheduledEventId) {
        this.scheduledEventId = scheduledEventId;
        this.limit = Optional.empty();
        this.withMember = Optional.empty();
        this.before = Optional.empty();
        this.after = Optional.empty();
    }

    public GetScheduledEventUsersBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetScheduledEventUsersBuilder withMember(boolean withMember) {
        this.withMember = Optional.of(withMember);
        return this;
    }

    public GetScheduledEventUsersBuilder before(long before) {
        this.before = Optional.of(before);
        return this;
    }

    public GetScheduledEventUsersBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetScheduledEventUsersRequest build() {
        return new GetScheduledEventUsersRequest(
                guildId, scheduledEventId, limit, withMember, before, after);
    }

    public GetScheduledEventUsersBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
