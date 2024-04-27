package com.javadiscord.jdi.internal.api.impl.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ExecuteSlackCompatibleWebhookRequest(long webhookId,
                                                   String webhookToken,
                                                   long threadId,
                                                   boolean waits) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .post()
                        .path("/webhooks/%s/%s/slack".formatted(webhookId, webhookToken));

        discordRequestBuilder.queryParam("thread_id", threadId);
        discordRequestBuilder.queryParam("wait", waits);

        return discordRequestBuilder;
    }
}
