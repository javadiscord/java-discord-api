package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.GetGuildRequest;

import java.util.Optional;

public final class GetGuildRequestBuilder {
    private long guildId;
    private Optional<Boolean> withCounts;

    public GetGuildRequestBuilder() {
        this.withCounts = Optional.empty();
    }

    public GetGuildRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildRequestBuilder setWithCounts(Optional<Boolean> withCounts) {
        this.withCounts = withCounts;
        return this;
    }

    public GetGuildRequest build() {
        return new GetGuildRequest(guildId, withCounts);
    }
}
