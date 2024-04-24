package com.javadiscord.jdi.internal.api.impl.channel;

import java.util.Map;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyChannelRequest(long channelId, String name, byte[] base64EncodedIcon)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .patch()
            .path("/channels/%s".formatted(channelId))
            .body(
                Map.of(
                    "name", name,
                    "icon", base64EncodedIcon
                )
            );
    }
}
