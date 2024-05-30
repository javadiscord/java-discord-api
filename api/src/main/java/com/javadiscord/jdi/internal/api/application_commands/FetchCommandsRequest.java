package com.javadiscord.jdi.internal.api.application_commands;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record FetchCommandsRequest() implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return null;
    }
}
