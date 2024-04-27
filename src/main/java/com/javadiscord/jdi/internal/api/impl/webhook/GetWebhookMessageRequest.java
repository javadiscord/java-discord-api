package com.javadiscord.jdi.internal.api.impl.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetWebhookMessageRequest(long webhookId,
                                       String webhookToken,
                                       long messageId,
                                       long threadId) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .get()
                        .path("/webhooks/%s/%s/messages/%s".formatted(webhookId, webhookToken, messageId));

        discordRequestBuilder.queryParam("thread_id", threadId);

        return discordRequestBuilder;

    }
}
