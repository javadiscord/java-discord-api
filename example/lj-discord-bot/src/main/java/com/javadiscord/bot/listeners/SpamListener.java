package com.javadiscord.bot.listeners;

import com.javadiscord.bot.utils.CurseWords;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class SpamListener {

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
                        )
                );
        }
    }

}
