package com.javadiscord.bot.listeners;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.StartThreadWithoutMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class SuggestionListener {

    @MessageCreate
    public void onMessage(Message message, Guild guild) {
        if (message.author().bot()) {
            return;
        }

        if (message.channelId() != 1244690778505216154L) {
            return;
        }

        guild.channel().createReaction(message.channelId(), message.id(), "thumbup");
        guild.channel().createReaction(message.channelId(), message.id(), "thumbsdown");

        guild.channel().startThreadWithoutMessage(
            new StartThreadWithoutMessageBuilder(
                message.channelId(),
                "Suggestion!"
            )
        );
    }

}
