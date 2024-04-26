package com.javadiscord.jdi.internal.api.channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record StartThreadThreadFromMessageRequest(
    long channelId,
    long messageId,
    String name,
    Optional<Integer> autoArchiveDuration,
    Optional<Integer> rateLimitPerUser
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        autoArchiveDuration.ifPresent(val -> body.put("auto_archive_duration", val));
        rateLimitPerUser.ifPresent(val -> body.put("rate_limit_per_user", val));

        return new DiscordRequestBuilder()
            .post()
            .path("/channels/%s/messages/%s/threads".formatted(channelId, messageId))
            .body(body);
    }
}
