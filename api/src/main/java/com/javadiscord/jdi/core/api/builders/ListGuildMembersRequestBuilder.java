package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ListGuildMembersRequest;

import java.util.Optional;

public final class ListGuildMembersRequestBuilder {
    private long guildId;
    private Optional<Integer> limit;
    private Optional<Long> after;

    public ListGuildMembersRequestBuilder() {
        this.limit = Optional.empty();
        this.after = Optional.empty();
    }

    public ListGuildMembersRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ListGuildMembersRequestBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public ListGuildMembersRequestBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public ListGuildMembersRequest build() {
        return new ListGuildMembersRequest(guildId, limit, after);
    }
}
