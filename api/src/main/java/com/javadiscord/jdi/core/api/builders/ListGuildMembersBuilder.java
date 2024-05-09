package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ListGuildMembersRequest;

import java.util.Optional;

public final class ListGuildMembersBuilder {
    private long guildId;
    private Optional<Integer> limit;
    private Optional<Long> after;

    public ListGuildMembersBuilder() {
        this.limit = Optional.empty();
        this.after = Optional.empty();
    }

    public ListGuildMembersBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ListGuildMembersBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public ListGuildMembersBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public ListGuildMembersRequest build() {
        return new ListGuildMembersRequest(guildId, limit, after);
    }
}
