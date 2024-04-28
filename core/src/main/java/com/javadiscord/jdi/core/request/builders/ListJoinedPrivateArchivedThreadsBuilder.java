package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.channel.ListJoinedPrivateArchivedThreadsRequest;

import java.util.Optional;

public class ListJoinedPrivateArchivedThreadsBuilder {
    private final long channelId;
    private Optional<Long> before;
    private Optional<Integer> limit;

    public ListJoinedPrivateArchivedThreadsBuilder(long channelId) {
        this.channelId = channelId;
        this.before = Optional.empty();
        this.limit = Optional.empty();
    }

    public ListJoinedPrivateArchivedThreadsBuilder setBefore(long before) {
        this.before = Optional.of(before);
        return this;
    }

    public ListJoinedPrivateArchivedThreadsBuilder setLimit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public ListJoinedPrivateArchivedThreadsRequest build() {
        return new ListJoinedPrivateArchivedThreadsRequest(channelId, before, limit);
    }
}
