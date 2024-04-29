package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyWebhookWithTokenRequest(long webhookId,
                                            String webhookToken,
                                            Optional<String> name,
                                            Optional<String> avatar,
                                            Optional<String> reason) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        avatar.ifPresent(val -> body.put("avatar", val));

        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .patch()
                        .path("/webhooks/%s/%s".formatted(webhookId, webhookToken))
                        .body(body);
        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
