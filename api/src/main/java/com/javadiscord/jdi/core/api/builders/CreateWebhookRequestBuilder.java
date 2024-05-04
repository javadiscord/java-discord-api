package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.webhook.CreateWebhookRequest;

import java.util.Optional;

public final class CreateWebhookRequestBuilder {
    private final long channelId;
    private final String name;
    private Optional<String> avatar;
    private Optional<String> reason;

    public CreateWebhookRequestBuilder(long channelId, String name) {
        this.channelId = channelId;
        this.name = name;
        this.avatar = Optional.empty();
        this.reason = Optional.empty();
    }

    public CreateWebhookRequestBuilder avatar(String avatar) {
        this.avatar = Optional.of(avatar);
        return this;
    }

    public CreateWebhookRequestBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public CreateWebhookRequest build() {
        return new CreateWebhookRequest(channelId, name, avatar, reason);
    }
}
