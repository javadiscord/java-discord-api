package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record ExecuteSlackCompatibleWebhookRequest(long webhookId,
                                                   String webhookToken,
                                                   Optional<Long> threadId,
                                                   Optional<Boolean> waits) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .post()
                        .path("/webhooks/%s/%s/slack".formatted(webhookId, webhookToken));

        threadId.ifPresent(val -> discordRequestBuilder.queryParam("thread_id", val));
        waits.ifPresent(val -> discordRequestBuilder.queryParam("wait", val));

        return discordRequestBuilder;
    }
}
