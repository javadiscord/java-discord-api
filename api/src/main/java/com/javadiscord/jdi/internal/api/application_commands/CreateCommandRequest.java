package com.javadiscord.jdi.internal.api.application_commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.javadiscord.jdi.core.api.builders.command.CommandOption;
import com.javadiscord.jdi.core.api.builders.command.CommandOptionType;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateCommandRequest(
    String name,
    CommandOptionType type,
    String description,
    List<CommandOption> options,
    Optional<Boolean> global,
    long guildId,
    long applicationId
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {

        AtomicReference<String> path =
            new AtomicReference<>("/applications/%s/commands".formatted(applicationId));

        global.ifPresent(
            val -> path.set("/applications/%s/guilds/%s/commands".formatted(applicationId, guildId))
        );

        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("type", type);
        body.put("description", description);

        if (!options.isEmpty()) {
            body.put("options", options);
        }

        return new DiscordRequestBuilder()
            .post()
            .body(body)
            .path(path.get());
    }
}
