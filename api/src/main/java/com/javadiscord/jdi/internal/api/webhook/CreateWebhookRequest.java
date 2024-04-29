package com.javadiscord.jdi.internal.api.webhook;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record CreateWebhookRequest(long channelId,
                                   String name,
                                   Optional<String> avatar,
                                   Optional<String> reason) implements DiscordRequest {

    public CreateWebhookRequest {
        if (name.length() > 80 || name.isEmpty()) throw new IllegalArgumentException("Name must be <= 80 characters");

        // Must follow Discord's naming guidelines
        String[] disallowedSubstrings = {"clyde", "discord", "@", "#", ":", "```"};
        for (String s : disallowedSubstrings) {
            if (name.toLowerCase().contains(s.toLowerCase())) { // Crude way of checking case insensitivity
                throw new IllegalArgumentException(
                        "Cannot include one of the following characters: clyde, discord, @, #, :, ```"
                );
            }
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        avatar.ifPresent(val -> body.put("avatar", val));

        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .post()
                        .path("/channels/%s/webhooks".formatted(channelId))
                        .body(body);

        reason.ifPresent(reason -> discordRequestBuilder.putHeader("X-Audit-Log-Reason", reason));

        return discordRequestBuilder;
    }
}
