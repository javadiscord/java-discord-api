package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.DeleteWebhookMessageRequest;

public final class DeleteWebhookMessageBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final long messageId;
    private Optional<Long> threadId;

    public DeleteWebhookMessageBuilder(long webhookId, String webhookToken, long messageId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.messageId = messageId;
        this.threadId = Optional.empty();
    }

    public DeleteWebhookMessageBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public DeleteWebhookMessageRequest build() {
        return new DeleteWebhookMessageRequest(webhookId, webhookToken, messageId, threadId);
    }
}
