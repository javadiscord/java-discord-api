package com.javadiscord.jdi.api.channel;

import java.util.Map;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record ChannelCreateInviteRequest(long channelId, int maxAge, int maxUses, boolean temporary)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/channels/%s/invites".formatted(channelId))
            .body(
                Map.of(
                    "max_age", maxAge,
                    "max_uses", maxUses,
                    "temporary", temporary
                )
            );
    }
}
