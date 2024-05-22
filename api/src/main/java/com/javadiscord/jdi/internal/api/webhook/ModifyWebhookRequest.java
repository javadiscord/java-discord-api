package com.javadiscord.jdi.internal.api.webhook;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyWebhookRequest(
    String webhookId,
    Optional<String> name,
    Optional<String> avatar,
    Optional<Long> channelId,
    Optional<String> reason
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        avatar.ifPresent(val -> body.put("avatar", val));
        channelId.ifPresent(val -> body.put("channel_id", val));

        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .patch()
                .path("/webhooks/%s".formatted(webhookId))
                .body(body);
        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
