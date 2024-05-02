package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.SearchGuildMembersRequest;

import java.util.Optional;

public final class SearchGuildMembersRequestBuilder {
    private long guildId;
    private Optional<String> query;
    private Optional<Integer> limit;

    public SearchGuildMembersRequestBuilder() {
        this.query = Optional.empty();
        this.limit = Optional.empty();
    }

    public SearchGuildMembersRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public SearchGuildMembersRequestBuilder setQuery(String query) {
        this.query = Optional.of(query);
        return this;
    }

    public SearchGuildMembersRequestBuilder setLimit(Integer limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public SearchGuildMembersRequest build() {
        return new SearchGuildMembersRequest(guildId, query, limit);
    }
}
