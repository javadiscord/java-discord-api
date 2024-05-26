package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;

public class ExampleSlashCommand {

    @SlashCommand(
        name = "quiz", description = "A fun Java quiz", options = {
            @CommandOption(
                name = "q1", description = "What is an Integer?", type = CommandOptionType.STRING
            ),
            @CommandOption(
                name = "q2", description = "What package is List in?", type = CommandOptionType.STRING
            ),
            @CommandOption(
                name = "q3", description = "What does JVM stand for?", type = CommandOptionType.STRING
            ),
            @CommandOption(
                name = "q4", description = "Is a String a primitive?", type = CommandOptionType.STRING
            ),
        }
    )
    public void handle(SlashCommandEvent event) {
        ApplicationCommandOption[] options = event.options();
        for (ApplicationCommandOption option : options) {
            System.out.println("Received " + option.name() + " value " + option.value());
        }
    }
}
