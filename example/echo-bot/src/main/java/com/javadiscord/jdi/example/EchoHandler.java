package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class EchoHandler {

    @MessageCreate
    public void echoMessages(Message message, Guild guild) {
        if (!message.author().bot()) {
            guild.channel().createMessage(
                new CreateMessageBuilder(message.channelId()).content(message.content())
            );
        }
    }
}
