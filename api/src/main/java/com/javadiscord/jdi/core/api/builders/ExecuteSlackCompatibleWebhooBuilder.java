package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.ExecuteSlackCompatibleWebhookRequest;

public final class ExecuteSlackCompatibleWebhooBuilder {
    private long webhookId;
    private String webhookToken;
    private Optional<Long> threadId;
    private Optional<Boolean> waits;

    public ExecuteSlackCompatibleWebhooBuilder(long webhookId, String webhookToken) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.threadId = Optional.empty();
        this.waits = Optional.empty();
    }

    public ExecuteSlackCompatibleWebhooBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public ExecuteSlackCompatibleWebhooBuilder waits(boolean waits) {
        this.waits = Optional.of(waits);
        return this;
    }

    public ExecuteSlackCompatibleWebhookRequest build() {
        return new ExecuteSlackCompatibleWebhookRequest(webhookId, webhookToken, threadId, waits);
    }
}
