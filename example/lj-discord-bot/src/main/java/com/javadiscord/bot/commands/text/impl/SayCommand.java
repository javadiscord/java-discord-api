package com.javadiscord.bot.commands.text.impl;

import com.javadiscord.bot.commands.text.TextCommand;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

public class SayCommand implements TextCommand {

    @Override
    public void handle(Guild guild, Message message, String input) {
        guild.channel().createMessage(new CreateMessageBuilder(message.channelId()).content(input));
    }
}
