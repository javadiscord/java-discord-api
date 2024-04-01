package com.javadiscord.jdi.internal.api.impl;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ChannelCreateInviteRequest(long channelId, int maxAge, int maxUses, boolean temporary)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .url("/channels/%s/invites".formatted(channelId))
                .body(this);
    }
}
