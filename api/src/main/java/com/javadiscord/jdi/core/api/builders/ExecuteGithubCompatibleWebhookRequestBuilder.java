package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.ExecuteGithubCompatibleWebhookRequest;

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

    public ExecuteGithubCompatibleWebhookRequestBuilder threadId(Optional<Long> threadId) {
        this.threadId = threadId;
        return this;
    }

    public ExecuteGithubCompatibleWebhookRequestBuilder waits(Optional<Boolean> waits) {
        this.waits = waits;
        return this;
    }

    public ExecuteGithubCompatibleWebhookRequest build() {
        return new ExecuteGithubCompatibleWebhookRequest(webhookId, webhookToken, threadId, waits);
    }
}
