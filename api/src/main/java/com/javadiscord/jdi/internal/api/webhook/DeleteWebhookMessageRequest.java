package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record DeleteWebhookMessageRequest(
        long webhookId, String webhookToken, long messageId, Optional<Long> threadId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .delete()
                        .path(
                                "/webhooks/%s/%s/messages/%s"
                                        .formatted(webhookId, webhookToken, messageId));

        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));

        return discordRequestBuilder;
    }
}
