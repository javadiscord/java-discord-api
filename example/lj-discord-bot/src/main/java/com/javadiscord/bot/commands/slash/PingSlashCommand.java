package com.javadiscord.bot.commands.slash;

import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;

public class PingSlashCommand {

    @SlashCommand(
        name = "ping", description = "Pong!"
    )
    public void ping(SlashCommandEvent event) {
        event.reply("Pong!");
    }
}
