package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.DeleteWebhookWithTokenRequest;

import java.util.Optional;

public final class DeleteWebhookWithTokenRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private Optional<String> reason;

    public DeleteWebhookWithTokenRequestBuilder(long webhookId, String webhookToken) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.reason = Optional.empty();
    }

    public DeleteWebhookWithTokenRequestBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public DeleteWebhookWithTokenRequest build() {
        return new DeleteWebhookWithTokenRequest(webhookId, webhookToken, reason);
    }
}
