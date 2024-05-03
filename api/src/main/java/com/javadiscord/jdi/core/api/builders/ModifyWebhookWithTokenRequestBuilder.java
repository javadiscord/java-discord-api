package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.ModifyWebhookWithTokenRequest;

public final class ModifyWebhookWithTokenRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private Optional<String> name;
    private Optional<String> avatar;
    private Optional<String> reason;

    public ModifyWebhookWithTokenRequestBuilder(long webhookId, String webhookToken) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.name = Optional.empty();
        this.avatar = Optional.empty();
        this.reason = Optional.empty();
    }

    public ModifyWebhookWithTokenRequestBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyWebhookWithTokenRequestBuilder avatar(String avatar) {
        this.avatar = Optional.of(avatar);
        return this;
    }

    public ModifyWebhookWithTokenRequestBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public ModifyWebhookWithTokenRequest build() {
        return new ModifyWebhookWithTokenRequest(webhookId, webhookToken, name, avatar, reason);
    }
}
