package com.javadiscord.jdi.internal.api.impl.invite;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteInviteRequest(String inviteCode) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().delete().path("/invites/%s".formatted(inviteCode));
    }
}
