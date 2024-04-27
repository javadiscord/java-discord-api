package com.javadiscord.jdi.internal.api.impl.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteWebhookMessageRequest(long webhookId,
                                          String webhookToken,
                                          long messageId,
                                          long threadId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .delete()
                        .path("/webhooks/%s/%s/messages/%s".formatted(webhookId, webhookToken, messageId));

        discordRequestBuilder.queryParam("thread_id", threadId);

        return discordRequestBuilder;

    }
}
