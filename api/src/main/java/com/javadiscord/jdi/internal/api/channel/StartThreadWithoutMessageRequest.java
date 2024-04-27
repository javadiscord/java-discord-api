package com.javadiscord.jdi.internal.api.channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record StartThreadWithoutMessageRequest(
    long channelId,
    String name,
    Optional<Integer> autoArchiveDuration,
    Optional<Integer> type,
    Optional<Boolean> invitable,
    Optional<Integer> rateLimitPerUser
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        autoArchiveDuration.ifPresent(val -> body.put("auto_archive_duration", val));
        type.ifPresent(val -> body.put("type", val));
        invitable.ifPresent(val -> body.put("invitation", val));
        rateLimitPerUser.ifPresent(val -> body.put("rate_limit", val));

        return new DiscordRequestBuilder()
            .post()
            .path("/channels/%s/threads".formatted(channelId))
            .body(body);
    }
}
