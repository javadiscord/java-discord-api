package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record DeleteWebhookRequest(String webhookId, Optional<String> reason) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .delete()
                        .path("/webhooks/%s".formatted(webhookId));
        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
