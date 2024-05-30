package com.javadiscord.bot.commands.text;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.message.Message;

public interface TextCommand {
    void handle(Guild guild, Message message, String input);
}
