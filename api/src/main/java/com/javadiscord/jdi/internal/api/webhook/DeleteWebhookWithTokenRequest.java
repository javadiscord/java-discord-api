package com.javadiscord.jdi.internal.api.webhook;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteWebhookWithTokenRequest(
    long webhookId,
    String webhookToken,
    Optional<String> reason
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .delete()
                .path("/webhooks/%s/%s".formatted(webhookId, webhookToken));
        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
