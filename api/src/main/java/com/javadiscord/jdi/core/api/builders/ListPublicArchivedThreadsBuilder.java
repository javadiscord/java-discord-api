package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.channel.ListPublicArchivedThreadsRequest;

import java.time.OffsetDateTime;
import java.util.Optional;

public class ListPublicArchivedThreadsBuilder {
    private final long channelId;
    private Optional<OffsetDateTime> before;
    private Optional<Integer> limit;

    public ListPublicArchivedThreadsBuilder(long channelId) {
        this.channelId = channelId;
        this.before = Optional.empty();
        this.limit = Optional.empty();
    }

    public ListPublicArchivedThreadsBuilder before(OffsetDateTime before) {
        this.before = Optional.of(before);
        return this;
    }

    public ListPublicArchivedThreadsBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public ListPublicArchivedThreadsRequest build() {
        return new ListPublicArchivedThreadsRequest(channelId, before, limit);
    }
}
