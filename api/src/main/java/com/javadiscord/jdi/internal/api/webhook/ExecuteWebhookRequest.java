package com.javadiscord.jdi.internal.api.webhook;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.javadiscord.jdi.core.models.channel.AllowedMentions;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.poll.Poll;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

public record ExecuteWebhookRequest(
    long webhookId,
    String webhookToken,
    Optional<Boolean> waits,
    Optional<Long> threadId,
    Optional<String> content,
    Optional<String> username,
    Optional<String> avatarUrl,
    Optional<Boolean> tts,
    Optional<List<Embed>> embeds,
    Optional<AllowedMentions> allowedMentions,
    Optional<List<Integer>> components,
    Optional<List<Path>> files,
    Optional<List<MessageAttachment>> attachments,
    Optional<Integer> flags,
    Optional<String> threadName,
    Optional<List<Long>> appliedTags,
    Optional<Poll> poll
) implements DiscordRequest {

    public ExecuteWebhookRequest {
        if (Stream.of(content, embeds, components, files, poll).noneMatch(Optional::isPresent)) {
            throw new IllegalArgumentException(
                "At least one of content, embeds, components, files, poll should be present"
            );
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder bodyBuilder = MultipartBodyPublisher.newBuilder();

        Map<String, Object> body = new HashMap<>();
        content.ifPresent(val -> body.put("content", val));
        username.ifPresent(val -> body.put("username", val));
        avatarUrl.ifPresent(val -> body.put("avatar_url", val));
        tts.ifPresent(val -> body.put("tts", val));
        embeds.ifPresent(val -> body.put("embeds", val));
        allowedMentions.ifPresent(val -> body.put("allowed_mentions", val));
        components.ifPresent(val -> body.put("components", val));
        attachments.ifPresent(val -> body.put("attachments", val));
        flags.ifPresent(val -> body.put("flags", val));
        threadName.ifPresent(val -> body.put("thread_name", val));
        appliedTags.ifPresent(val -> body.put("applied_tags", val));
        poll.ifPresent(val -> body.put("poll", val));

        bodyBuilder.textPart("payload_json", body);

        files.ifPresent(
            paths -> {
                for (int i = 0; i < paths.size(); i++) {
                    try {
                        bodyBuilder.filePart("file[%d]".formatted(i), paths.get(i));
                    } catch (FileNotFoundException ignored) {
                        /* Ignore */
                    }
                }
            }
        );

        DiscordRequestBuilder discordRequestBuilder =
            new DiscordRequestBuilder()
                .post()
                .path("/webhooks/%s/%s".formatted(webhookId, webhookToken))
                .multipartBody(bodyBuilder.build());

        waits.ifPresent(val -> discordRequestBuilder.queryParam("wait", val));
        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));

        return discordRequestBuilder;
    }
}
