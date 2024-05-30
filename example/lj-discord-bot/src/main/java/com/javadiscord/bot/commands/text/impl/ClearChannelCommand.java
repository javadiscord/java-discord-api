package com.javadiscord.bot.commands.text.impl;

import java.util.List;

import com.javadiscord.bot.commands.text.TextCommand;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.FetchChannelMessagesBuilder;
import com.javadiscord.jdi.core.models.message.Message;

public class ClearChannelCommand implements TextCommand {
    @Override
    public void handle(Guild guild, Message message, String input) {
        int limit;
        try {
            limit = Integer.parseInt(input);
            if (limit <= 1) {
                limit = 2;
            }
            if (limit > 100) {
                limit = 100;
            }
        } catch (Exception e) {
            limit = 10;
        }

        guild.channel()
            .fetchChannelMessages(new FetchChannelMessagesBuilder(message.channelId(), limit))
            .onSuccess(messages -> {

                List<Long> ids =
                    messages.stream()
                        .map(Message::id)
                        .toList();

                guild.channel().bulkDeleteMessages(message.channelId(), ids);
            });

    }
}
