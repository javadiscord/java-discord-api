package com.javadiscord.jdi.internal.api.application_commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.api.builders.command.CallbackMessage;
import com.javadiscord.jdi.core.api.builders.command.CallbackResponseType;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record RespondCommandRequest(
    CallbackResponseType type,
    Optional<CallbackMessage> message,
    long interactionId,
    String interactionToken
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {

        Map<String, Object> body = new HashMap<>();
        body.put("type", type.getValue());

        message.ifPresent(m -> body.put("data", m));

        return new DiscordRequestBuilder()
            .post()
            .body(body)
            .path("/interactions/%s/%s/callback".formatted(interactionId, interactionToken));
    }
}
