package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.DeleteWebhookMessageRequest;

import java.util.Optional;

public final class DeleteWebhookMessageRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final long messageId;
    private Optional<Long> threadId;

    public DeleteWebhookMessageRequestBuilder(long webhookId, String webhookToken, long messageId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.messageId = messageId;
        this.threadId = Optional.empty();
    }

    public DeleteWebhookMessageRequestBuilder setThreadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public DeleteWebhookMessageRequest build() {
        return new DeleteWebhookMessageRequest(webhookId, webhookToken, messageId, threadId);
    }
}
