package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.GetWebhookMessageRequest;

public final class GetWebhookMessageBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final long messageId;
    private Optional<Long> threadId;

    public GetWebhookMessageBuilder(long webhookId, String webhookToken, long messageId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.messageId = messageId;
        this.threadId = Optional.empty();
    }

    public GetWebhookMessageBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public GetWebhookMessageRequest build() {
        return new GetWebhookMessageRequest(webhookId, webhookToken, messageId, threadId);
    }
}
