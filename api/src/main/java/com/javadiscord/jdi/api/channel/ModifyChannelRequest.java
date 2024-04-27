package com.javadiscord.jdi.api.channel;

import java.util.Map;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record ModifyChannelRequest(long channelId, String name, String base64EncodedIcon)
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
