package com.javadiscord.jdi.core.api.builders.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.application_commands.CreateCommandRequest;

public class CommandBuilder {
    private final String name;
    private final CommandOptionType type;
    private final String description;
    private final List<CommandOption> options;

    private Optional<Boolean> global;

    private long applicationId;
    private long guildId;

    public CommandBuilder(
        String name, String description
    ) {
        this.name = name;
        this.type = CommandOptionType.SUB_COMMAND;
        this.description = description;
        this.options = new ArrayList<>();
        this.global = Optional.empty();
    }

    public CommandBuilder addOption(CommandOption option) {
        options.add(option);
        return this;
    }

    public CommandBuilder global(boolean global) {
        this.global = Optional.of(global);
        return this;
    }

    public CommandBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public CommandBuilder applicationId(long applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public CreateCommandRequest build() {
        return new CreateCommandRequest(
            name,
            type,
            description,
            options,
            global,
            guildId,
            applicationId
        );
    }
}
