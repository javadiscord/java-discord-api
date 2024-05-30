package com.javadiscord.jdi.core.api.builders.command;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.application_commands.EditCommandRequest;
import com.javadiscord.jdi.internal.api.application_commands.RespondCommandRequest;

public class CallbackMessageBuilder {
    private final CallbackResponseType type;
    private final long interactionId;
    private final String interactionToken;
    private Optional<CallbackMessage> message;
    private long applicationId;

    public CallbackMessageBuilder(
        CallbackResponseType type, long interactionId, String interactionToken
    ) {
        this.type = type;
        this.interactionId = interactionId;
        this.interactionToken = interactionToken;
        this.message = Optional.empty();
    }

    public CallbackMessageBuilder message(CallbackMessage message) {
        this.message = Optional.of(message);
        return this;
    }

    public CallbackMessageBuilder applicationId(long applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public RespondCommandRequest build() {
        return new RespondCommandRequest(type, message, interactionId, interactionToken);
    }

    public EditCommandRequest buildEdit() {
        return new EditCommandRequest(type, message, applicationId, interactionToken);
    }
}
