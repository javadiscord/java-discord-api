package com.javadiscord.jdi.internal.api.impl.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetWebhookRequest(long webhookId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/webhooks/%s".formatted(webhookId));
    }
}
