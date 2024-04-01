package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Map;

public record ChannelCreateInviteRequest(long channelId, int maxAge, int maxUses, boolean temporary)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .url("/channels/%s/invites".formatted(channelId))
                .body(
                        Map.of(
                                "max_age", maxAge,
                                "max_uses", maxUses,
                                "temporary", temporary));
    }
}
