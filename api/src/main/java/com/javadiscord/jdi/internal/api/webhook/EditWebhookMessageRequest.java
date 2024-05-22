package com.javadiscord.jdi.internal.api.webhook;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.AllowedMentions;
import com.javadiscord.jdi.core.models.message.Component;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

public record EditWebhookMessageRequest(
    long webhookId,
    String webhookToken,
    long messageId,
    Optional<Long> threadId,
    Optional<String> content,
    Optional<List<Embed>> embeds,
    Optional<AllowedMentions> allowedMentions,
    Optional<List<Component>> components,
    Optional<List<Path>> files,
    Optional<List<MessageAttachment>> attachments
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder bodyBuilder = MultipartBodyPublisher.newBuilder();

        Map<String, Object> body = new HashMap<>();
        content.ifPresent(val -> body.put("content", val));
        embeds.ifPresent(val -> body.put("embeds", val));
        allowedMentions.ifPresent(val -> body.put("allowed_mentions", val));
        components.ifPresent(val -> body.put("components", val));
        attachments.ifPresent(val -> body.put("attachments", val));

        bodyBuilder.textPart("payload_json", body);

        files.ifPresent(
            paths -> {
                for (int i = 0; i < paths.size(); i++) {
                    try {
                        bodyBuilder.filePart("file[%d]".formatted(i), paths.get(i));
                    } catch (FileNotFoundException ignored) {}
                }
            }
        );

        DiscordRequestBuilder discordRequestBuilder =
            new DiscordRequestBuilder()
                .patch()
                .path(
                    "/webhooks/%s/%s/messages/%s"
                        .formatted(webhookId, webhookToken, messageId)
                )
                .multipartBody(bodyBuilder.build());

        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));
        return discordRequestBuilder;
    }
}
