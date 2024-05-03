package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.webhook.CreateWebhookRequest;

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

    public CreateWebhookRequestBuilder setAvatar(String avatar) {
        this.avatar = Optional.of(avatar);
        return this;
    }

    public CreateWebhookRequestBuilder setReason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public CreateWebhookRequest build() {
        return new CreateWebhookRequest(channelId, name, avatar, reason);
    }
}
