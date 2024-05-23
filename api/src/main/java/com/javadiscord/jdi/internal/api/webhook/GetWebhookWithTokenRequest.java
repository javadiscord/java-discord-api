package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetWebhookWithTokenRequest(
    long webhookId,
    String webhookToken
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .get()
            .path("/webhooks/%s/%s".formatted(webhookId, webhookToken));
    }
}
