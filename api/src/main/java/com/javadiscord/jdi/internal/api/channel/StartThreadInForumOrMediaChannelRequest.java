package com.javadiscord.jdi.internal.api.channel;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.javadiscord.jdi.core.models.channel.ForumAndMediaThreadMessageParams;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record StartThreadInForumOrMediaChannelRequest(
        long channelId,
        String name,
        Optional<Integer> autoArchiveDuration,
        Optional<Integer> rateLimitPerUser,
        ForumAndMediaThreadMessageParams message,
        Optional<List<Long>> appliedTags,
        Optional<List<Path>> files)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder bodyBuilder = MultipartBodyPublisher.newBuilder();

        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        autoArchiveDuration.ifPresent(val -> body.put("auto_archive_duration", val));
        rateLimitPerUser.ifPresent(val -> body.put("rate_limit_per_user", val));
        body.put("message", message);
        appliedTags.ifPresent(val -> body.put("applied_tags", val));
        files.ifPresent(val -> body.put("files", val));

        bodyBuilder.textPart("payload_json", body);

        files.ifPresent(
                paths -> {
                    for (int i = 0; i < paths.size(); i++) {
                        try {
                            bodyBuilder.filePart("file[%d]".formatted(i), paths.get(i));
                        } catch (FileNotFoundException ignored) {
                        }
                    }
                });

        return new DiscordRequestBuilder()
                .post()
                .path("/channels/%s/threads".formatted(channelId))
                .multipartBody(bodyBuilder.build());
    }
}
