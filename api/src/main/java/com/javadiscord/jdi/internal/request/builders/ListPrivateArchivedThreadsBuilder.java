package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.channel.ListPrivateArchivedThreadsRequest;

import java.time.OffsetDateTime;
import java.util.Optional;

public class ListPrivateArchivedThreadsBuilder {
    private final long channelId;
    private Optional<OffsetDateTime> before;
    private Optional<Integer> limit;

    public ListPrivateArchivedThreadsBuilder(long channelId) {
        this.channelId = channelId;
        this.before = Optional.empty();
        this.limit = Optional.empty();
    }

    public ListPrivateArchivedThreadsBuilder setBefore(OffsetDateTime before) {
        this.before = Optional.ofNullable(before);
        return this;
    }

    public ListPrivateArchivedThreadsBuilder setLimit(int limit) {
        this.limit = Optional.ofNullable(limit);
        return this;
    }

    public ListPrivateArchivedThreadsRequest build() {
        return new ListPrivateArchivedThreadsRequest(channelId, before, limit);
    }
}