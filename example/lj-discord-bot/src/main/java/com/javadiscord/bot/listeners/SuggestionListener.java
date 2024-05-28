package com.javadiscord.bot.listeners;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.StartThreadFromMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class SuggestionListener {

    @MessageCreate
    public void onMessage(Message message, Guild guild) {
        if (message.fromBot()) {
            return;
        }

        if (message.channelId() != 1244690778505216154L) {
            return;
        }

        // guild.channel().createReaction(message.channelId(), message.id(), "thumbup");
        // guild.channel().createReaction(message.channelId(), message.id(),
        // "thumbsdown");

        String title =
            message.content().length() > 60
                ? message.content().substring(0, 60)
                : message.content();

        guild.channel().startThreadFromMessage(
            new StartThreadFromMessageBuilder(
                message.channelId(),
                message.id(),
                title
            )
        ).onError(System.err::println)
            .onSuccess(System.out::println);
    }

}
