package com.javadiscord.jdi.core.api.builders.command;

import java.util.ArrayList;
import java.util.List;

public class CommandOption {
    private final String name;
    private final String description;
    private final CommandOptionType type;
    private boolean required;
    private final List<CommandOptionChoice> choices;

    public CommandOption(String name, String description, CommandOptionType type) {
        this(name, description, type, true);
    }

    public CommandOption(
        String name, String description, CommandOptionType type, boolean required
    ) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.required = required;
        this.choices = new ArrayList<>();
    }

    public CommandOption addChoice(String name, String value) {
        choices.add(new CommandOptionChoice(name, value));
        return this;
    }

    public CommandOption setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandOptionType getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    public List<CommandOptionChoice> getChoices() {
        return choices;
    }
}
