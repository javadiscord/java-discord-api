package com.javadiscord.jdi.internal.api.application_commands;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteCommandRequest(
    long applicationId,
    Optional<Long> guildId,
    long commandId
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        // TODO: Implement
        // https://discord.com/developers/docs/interactions/application-commands#updating-and-deleting-a-command
        return null;
    }
}
