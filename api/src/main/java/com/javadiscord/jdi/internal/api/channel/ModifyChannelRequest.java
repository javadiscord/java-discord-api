package com.javadiscord.jdi.internal.api.channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyChannelRequest(
    long channelId,
    Optional<String> name,
    Optional<String> base64EncodedIcon
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();

        name.ifPresent(val -> body.put("name", val));
        base64EncodedIcon.ifPresent(val -> body.put("icon", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/channels/%s".formatted(channelId))
            .body(body);
    }
}
