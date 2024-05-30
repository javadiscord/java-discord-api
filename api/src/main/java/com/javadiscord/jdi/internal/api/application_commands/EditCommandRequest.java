package com.javadiscord.jdi.internal.api.application_commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.api.builders.command.CallbackMessage;
import com.javadiscord.jdi.core.api.builders.command.CallbackResponseType;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record EditCommandRequest(
    CallbackResponseType type,
    Optional<CallbackMessage> message,
    long applicationId,
    String interactionToken
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("type", type.getValue());

        message.ifPresent(m -> {
            body.put("tts", m.isTts());
            body.put("content", m.getContent());

            if (m.getEmbeds() != null) {
                body.put("embeds", m.getEmbeds());
            }

            if (m.getAllowedMentions() != null) {
                body.put("allowed_mentions", m.getAllowedMentions());
            }

            body.put("flags", m.getFlags());

            if (m.getComponents() != null) {
                body.put("components", m.getComponents());
            }

            if (m.getAttachments() != null) {
                body.put("attachments", m.getAttachments());
            }

            if (m.getPoll() != null) {
                body.put("poll", m.getPoll());
            }
        });

        return new DiscordRequestBuilder()
            .patch()
            .body(body)
            .path("/webhooks/%s/%s/messages/@original".formatted(applicationId, interactionToken));
    }
}
