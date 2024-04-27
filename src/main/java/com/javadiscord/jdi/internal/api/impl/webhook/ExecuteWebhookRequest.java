package com.javadiscord.jdi.internal.api.impl.webhook;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.message.MessageAttachment;
import com.javadiscord.jdi.internal.models.poll.Poll;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public record ExecuteWebhookRequest(
        long webhookId,
        String webhookToken,
        Optional<Boolean> waits,
        Optional<Long> threadId,
        Optional<String> content,
        Optional<String> username,
        Optional<String> avatarUrl,
        Optional<Boolean> tts,
        Optional<List<Object>> embeds,
        Optional<Object> allowedMentions,
        Optional<List<Object>> components,
        Optional<Object> files,
        String payloadJson,
        Optional<List<MessageAttachment>> attachments,
        Optional<Integer> flags,
        Optional<String> threadName,
        Optional<List<Long>> appliedTags,
        Optional<Poll> poll
) implements DiscordRequest {

    public ExecuteWebhookRequest {
        if (Stream.of(content, embeds, components, files, poll).noneMatch(Optional::isPresent)) {
            throw new IllegalArgumentException("At least one of content, embeds, components, files, poll should be present");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .post()
                        .path("/webhooks/%s/%s".formatted(webhookId, webhookToken))
                        .multipartBody(
                                MultipartBodyPublisher.newBuilder()
                                        .textPart("payload_json", payloadJson)
                                        .build()
                        );

        waits.ifPresent(val -> discordRequestBuilder.queryParam("wait", val));
        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));

        return discordRequestBuilder;
    }
}
