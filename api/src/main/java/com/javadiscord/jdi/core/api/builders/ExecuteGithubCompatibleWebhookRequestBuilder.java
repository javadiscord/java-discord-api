package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.ExecuteGithubCompatibleWebhookRequest;

import java.util.Optional;

public final class ExecuteGithubCompatibleWebhookRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private Optional<Long> threadId;
    private Optional<Boolean> waits;

    public ExecuteGithubCompatibleWebhookRequestBuilder(long webhookId, String webhookToken) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.threadId = Optional.empty();
        this.waits = Optional.empty();
    }

    public ExecuteGithubCompatibleWebhookRequestBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public ExecuteGithubCompatibleWebhookRequestBuilder waits(boolean waits) {
        this.waits = Optional.of(waits);
        return this;
    }

    public ExecuteGithubCompatibleWebhookRequest build() {
        return new ExecuteGithubCompatibleWebhookRequest(webhookId, webhookToken, threadId, waits);
    }
}
