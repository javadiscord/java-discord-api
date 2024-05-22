package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.GetGuildBansRequest;

public class GetGuildBansBuilder {
    private long guildId;
    private Optional<Integer> limit;
    private Optional<Long> before;
    private Optional<Long> after;

    public GetGuildBansBuilder() {
        this.limit = Optional.empty();
        this.before = Optional.empty();
        this.after = Optional.empty();
    }

    public GetGuildBansBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildBansBuilder setLimit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetGuildBansBuilder setBefore(long before) {
        this.before = Optional.of(before);
        return this;
    }

    public GetGuildBansBuilder setAfter(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetGuildBansRequest build() {
        return new GetGuildBansRequest(guildId, limit, before, after);
    }
}
