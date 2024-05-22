package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.DeleteWebhookRequest;

public final class DeleteWebhookBuilder {
    private final long webhookId;
    private Optional<String> reason;

    public DeleteWebhookBuilder(long webhookId) {
        this.webhookId = webhookId;
        this.reason = Optional.empty();
    }

    public DeleteWebhookBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public DeleteWebhookRequest build() {
        return new DeleteWebhookRequest(webhookId, reason);
    }
}
