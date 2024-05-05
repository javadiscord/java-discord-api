package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.GetGuildRequest;

import java.util.Optional;

public final class GetGuildBuilder {
    private long guildId;
    private Optional<Boolean> withCounts;

    public GetGuildBuilder() {
        this.withCounts = Optional.empty();
    }

    public GetGuildBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildBuilder withCounts(boolean withCounts) {
        this.withCounts = Optional.of(withCounts);
        return this;
    }

    public GetGuildRequest build() {
        return new GetGuildRequest(guildId, withCounts);
    }
}
