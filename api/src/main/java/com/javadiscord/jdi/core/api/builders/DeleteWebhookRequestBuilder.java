package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.DeleteWebhookRequest;

import java.util.Optional;

public final class DeleteWebhookRequestBuilder {
    private final long webhookId;
    private Optional<String> reason;

    public DeleteWebhookRequestBuilder(long webhookId) {
        this.webhookId = webhookId;
        this.reason = Optional.empty();
    }

    public DeleteWebhookRequestBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public DeleteWebhookRequest build() {
        return new DeleteWebhookRequest(webhookId, reason);
    }
}
