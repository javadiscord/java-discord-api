package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.channel.ListThreadMembersRequest;

public class ListThreadMembersBuilder {
    private final long channelId;
    private Optional<Boolean> withMember;
    private Optional<Long> after;
    private Optional<Integer> limit;

    public ListThreadMembersBuilder(long channelId) {
        this.channelId = channelId;
        this.withMember = Optional.empty();
        this.after = Optional.empty();
        this.limit = Optional.empty();
    }

    public ListThreadMembersBuilder withMember(boolean withMember) {
        this.withMember = Optional.of(withMember);
        return this;
    }

    public ListThreadMembersBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public ListThreadMembersBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public ListThreadMembersRequest build() {
        return new ListThreadMembersRequest(channelId, withMember, after, limit);
    }
}
