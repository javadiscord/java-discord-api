package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.user.GetCurrentUserGuildsRequest;

import java.util.Optional;

public class GetCurrentUserGuildsBuilder {
    private Optional<Long> before;
    private Optional<Long> after;
    private Optional<Integer> limit;
    private Optional<Boolean> withCounts;

    public GetCurrentUserGuildsBuilder() {
        this.before = Optional.empty();
        this.after = Optional.empty();
        this.limit = Optional.empty();
        this.withCounts = Optional.empty();
    }

    public GetCurrentUserGuildsBuilder before(long before) {
        this.before = Optional.of(before);
        return this;
    }

    public GetCurrentUserGuildsBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetCurrentUserGuildsBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetCurrentUserGuildsBuilder withCounts(boolean withCounts) {
        this.withCounts = Optional.of(withCounts);
        return this;
    }

    public GetCurrentUserGuildsRequest build() {
        return new GetCurrentUserGuildsRequest(before, after, limit, withCounts);
    }
}
