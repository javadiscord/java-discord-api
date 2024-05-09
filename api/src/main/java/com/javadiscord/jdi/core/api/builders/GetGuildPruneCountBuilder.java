package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.GetGuildPruneCountRequest;

import java.util.List;
import java.util.Optional;

public final class GetGuildPruneCountBuilder {
    private long guildId;
    private Optional<Integer> days;
    private Optional<List<Long>> includeRoles;

    public GetGuildPruneCountBuilder() {
        this.days = Optional.empty();
        this.includeRoles = Optional.empty();
    }

    public GetGuildPruneCountBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildPruneCountBuilder days(int days) {
        this.days = Optional.of(days);
        return this;
    }

    public GetGuildPruneCountBuilder includeRoles(List<Long> includeRoles) {
        this.includeRoles = Optional.of(includeRoles);
        return this;
    }

    public GetGuildPruneCountRequest build() {
        return new GetGuildPruneCountRequest(guildId, days, includeRoles);
    }
}
