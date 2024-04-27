package com.javadiscord.jdi.api.invite;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record DeleteInviteRequest(String inviteCode) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().delete().path("/invites/%s".formatted(inviteCode));
    }
}
