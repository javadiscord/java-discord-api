package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.invite.GetInviteRequest;

import java.util.Optional;

public class GetInviteBuilder {
    private final String inviteCode;
    private Optional<Boolean> withCounts;
    private Optional<Boolean> withExpiration;
    private Optional<Long> guildScheduledEventId;

    public GetInviteBuilder(String inviteCode) {
        this.inviteCode = inviteCode;
        this.withCounts = Optional.empty();
        this.withExpiration = Optional.empty();
        this.guildScheduledEventId = Optional.empty();
    }

    public GetInviteBuilder withCounts(boolean withCounts) {
        this.withCounts = Optional.of(withCounts);
        return this;
    }

    public GetInviteBuilder withExpiration(boolean withExpiration) {
        this.withExpiration = Optional.of(withExpiration);
        return this;
    }

    public GetInviteBuilder withGuildScheduledEventId(long guildScheduledEventId) {
        this.guildScheduledEventId = Optional.of(guildScheduledEventId);
        return this;
    }

    public GetInviteRequest build() {
        return new GetInviteRequest(inviteCode, withCounts, withExpiration, guildScheduledEventId);
    }
}
