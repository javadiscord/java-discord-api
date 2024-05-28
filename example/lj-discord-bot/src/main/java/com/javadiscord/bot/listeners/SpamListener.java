package com.javadiscord.bot.listeners;

import com.javadiscord.bot.utils.CurseWords;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventListener
public class SpamListener {
    private static final Logger LOGGER = LogManager.getLogger(SlashCommandListener.class);

    @MessageCreate
    public void onMessage(Message message, Guild guild) {
        if (message.fromUser() && CurseWords.containsCurseWord(message.content())) {
            guild.channel().deleteMessage(message.channelId(), message.id());
            guild.user()
                .createDM(message.id())
                .onSuccess(
                    channel -> guild.channel()
                        .createMessage(
                            new CreateMessageBuilder(channel.id()).content(
                                """
                                    Your message has been removed for containing words blacklisted by this server!
                                    Please avoid sending such messages in the future. Thank you.

                                    The message you sent:
                                    > %s
                                    """
                                    .formatted(message.content())
                            )
                        ).onError(System.err::println)
                );
        }
    }

}
