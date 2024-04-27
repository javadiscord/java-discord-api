package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record StartThreadInForumOrMediaChannelRequest(
        long channelId,
        String name,
        Optional<Integer> autoArchiveDuration,
        Optional<Integer> rateLimitPerUser,
        Object message, // TODO: Create Forum and Media Thread Message Params Object
        Optional<List<Long>> appliedTags,
        Optional<Object> files, // TODO: Create Files object
        Optional<String> payloadJson)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        autoArchiveDuration.ifPresent(val -> body.put("auto_archive_duration", val));
        rateLimitPerUser.ifPresent(val -> body.put("rate_limit_per_user", val));
        body.put("message", message);
        appliedTags.ifPresent(val -> body.put("applied_tags", val));
        files.ifPresent(val -> body.put("files", val));
        payloadJson.ifPresent(val -> body.put("payload_json", val));

        return new DiscordRequestBuilder()
                .post()
                .path("/channels/%s/threads".formatted(channelId))
                .body(body);
    }
}
