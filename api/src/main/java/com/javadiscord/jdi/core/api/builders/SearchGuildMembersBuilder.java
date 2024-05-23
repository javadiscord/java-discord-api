package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.SearchGuildMembersRequest;

public final class SearchGuildMembersBuilder {
    private long guildId;
    private Optional<String> query;
    private Optional<Integer> limit;

    public SearchGuildMembersBuilder() {
        this.query = Optional.empty();
        this.limit = Optional.empty();
    }

    public SearchGuildMembersBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public SearchGuildMembersBuilder query(String query) {
        this.query = Optional.of(query);
        return this;
    }

    public SearchGuildMembersBuilder limit(Integer limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public SearchGuildMembersRequest build() {
        return new SearchGuildMembersRequest(guildId, query, limit);
    }
}
