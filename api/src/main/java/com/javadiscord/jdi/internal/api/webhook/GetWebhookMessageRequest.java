package com.javadiscord.jdi.internal.api.webhook;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetWebhookMessageRequest(
    long webhookId,
    String webhookToken,
    long messageId,
    Optional<Long> threadId
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .get()
                .path(
                    "/webhooks/%s/%s/messages/%s"
                        .formatted(webhookId, webhookToken, messageId)
                );

        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));

        return discordRequestBuilder;
    }
}
