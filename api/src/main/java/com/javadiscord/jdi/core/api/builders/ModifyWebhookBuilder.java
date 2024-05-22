package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.ModifyWebhookRequest;

public final class ModifyWebhookBuilder {
    private final String webhookId;
    private Optional<String> name;
    private Optional<String> avatar;
    private Optional<Long> channelId;
    private Optional<String> reason;

    public ModifyWebhookBuilder(String webhookId) {
        this.webhookId = webhookId;
        this.name = Optional.empty();
        this.avatar = Optional.empty();
        this.channelId = Optional.empty();
        this.reason = Optional.empty();
    }

    public ModifyWebhookBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyWebhookBuilder avatar(String avatar) {
        this.avatar = Optional.of(avatar);
        return this;
    }

    public ModifyWebhookBuilder channelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyWebhookBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public ModifyWebhookRequest build() {
        return new ModifyWebhookRequest(webhookId, name, avatar, channelId, reason);
    }
}
