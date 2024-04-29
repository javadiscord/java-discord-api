package com.javadiscord.jdi.internal.api.webhook;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.message.MessageAttachment;
import com.javadiscord.jdi.internal.models.message.embed.Embed;

import java.util.List;
import java.util.Optional;

public record EditWebhookMessageRequest(long webhookId,
                                        String webhookToken,
                                        long messageId,
                                        Optional<Long> threadId,
                                        Optional<String> content,
                                        Optional<List<Embed>> embeds,
                                        Optional<Object> allowedMentions,
                                        Optional<List<Object>> components,
                                        Optional<Object> files,
                                        Optional<String> payloadJson,
                                        Optional<List<MessageAttachment>> attachments
                                        ) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {

        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .patch()
                        .path("/webhooks/%s/%s/messages/%s".formatted(webhookId, webhookToken, messageId))
                        .multipartBody(
                                MultipartBodyPublisher.newBuilder()
                                        .textPart("content", content)
                                        .textPart("embeds", embeds)
                                        .textPart("allowed_mentions", allowedMentions)
                                        .textPart("components", components)
                                        .textPart("files", files)
                                        .textPart("payload_json", payloadJson)
                                        .textPart("attachments", attachments)
                                        .build()
                        );

        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));
        return discordRequestBuilder;
    }
}
