package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.ModifyWebhookRequest;

import java.util.Optional;

public final class ModifyWebhookRequestBuilder {
    private final String webhookId;
    private Optional<String> name;
    private Optional<String> avatar;
    private Optional<Long> channelId;
    private Optional<String> reason;

    public ModifyWebhookRequestBuilder(String webhookId) {
        this.webhookId = webhookId;
        this.name = Optional.empty();
        this.avatar = Optional.empty();
        this.channelId = Optional.empty();
        this.reason = Optional.empty();
    }

    public ModifyWebhookRequestBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyWebhookRequestBuilder avatar(String avatar) {
        this.avatar = Optional.of(avatar);
        return this;
    }

    public ModifyWebhookRequestBuilder channelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyWebhookRequestBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public ModifyWebhookRequest build() {
        return new ModifyWebhookRequest(webhookId, name, avatar, channelId, reason);
    }
}
