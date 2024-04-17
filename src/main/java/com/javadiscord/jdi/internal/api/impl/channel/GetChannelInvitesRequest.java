package com.javadiscord.jdi.internal.api.impl.channel;


import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetChannelInvitesRequest(long channelId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .get()
                .path("/channels/%s/invites".formatted(channelId));
    }
}
