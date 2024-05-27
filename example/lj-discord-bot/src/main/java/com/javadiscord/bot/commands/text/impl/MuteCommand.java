package com.javadiscord.bot.commands.text.impl;

import com.javadiscord.bot.commands.text.TextCommand;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.guild.Role;
import com.javadiscord.jdi.core.models.message.Message;

public class MuteCommand implements TextCommand {

    @Override
    public void handle(Guild guild, Message message, String input) {
        guild.guild().guildRoles().onSuccess(roles -> {
            for (Role role : roles) {
                if (role.name().equals("Muted")) {
                    guild.guild().addGuildMemberRole(message.author().id(), role.id());
                    break;
                }
            }
        });
    }
}
