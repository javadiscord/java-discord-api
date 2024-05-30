package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.command.CommandBuilder;
import com.javadiscord.jdi.core.api.builders.command.CommandOption;

public class InteractionRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;
    private final long applicationId;

    public InteractionRequest(
        DiscordResponseParser responseParser, long guildId, long applicationId
    ) {
        this.responseParser = responseParser;
        this.guildId = guildId;
        this.applicationId = applicationId;
    }

    public AsyncResponse<Void> createInteraction(CommandBuilder builder) {
        return responseParser
            .callAndParse(Void.class, builder.applicationId(applicationId).build());
    }

    public AsyncResponse<Void> createSlashCommand(
        String name,
        String description,
        CommandOption... options
    ) {
        CommandBuilder builder =
            new CommandBuilder(
                name,
                description
            );
        for (CommandOption option : options) {
            builder.addOption(option);
        }
        builder.applicationId(applicationId);
        return createInteraction(builder);
    }
}
