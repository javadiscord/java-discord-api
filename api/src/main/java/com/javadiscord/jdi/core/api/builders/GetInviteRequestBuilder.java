package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.invite.GetInviteRequest;

import java.util.Optional;

public class GetInviteRequestBuilder {
    private final String inviteCode;
    private Optional<Boolean> withCounts;
    private Optional<Boolean> withExpiration;
    private Optional<Long> guildScheduledEventId;

    public GetInviteRequestBuilder(String inviteCode) {
        this.inviteCode = inviteCode;
        this.withCounts = Optional.empty();
        this.withExpiration = Optional.empty();
        this.guildScheduledEventId = Optional.empty();
    }

    public GetInviteRequestBuilder withCounts(Boolean withCounts) {
        this.withCounts = Optional.of(withCounts);
        return this;
    }

    public GetInviteRequestBuilder withExpiration(Boolean withExpiration) {
        this.withExpiration = Optional.of(withExpiration);
        return this;
    }

    public GetInviteRequestBuilder withGuildScheduledEventId(Long guildScheduledEventId) {
        this.guildScheduledEventId = Optional.of(guildScheduledEventId);
        return this;
    }

    public GetInviteRequest build() {
        return new GetInviteRequest(inviteCode, withCounts, withExpiration, guildScheduledEventId);
    }
}
