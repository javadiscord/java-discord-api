package com.javadiscord.jdi.internal.api.application_commands;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteCommandRequest(
    long applicationId,
    long guildId,
    long commandId,
    boolean global
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        String path;

        if (global) {
            path = "applications/%s/commands/%s".formatted(applicationId, commandId);
        } else {
            path =
                "applications/%s/guilds/%s/commands/%s"
                    .formatted(applicationId, guildId, commandId);
        }

        return new DiscordRequestBuilder()
            .delete()
            .path(path);
    }
}
