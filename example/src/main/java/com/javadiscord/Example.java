package com.javadiscord;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class Example {

    @MessageCreate
    public void onMessageCreate(Message message, Guild guild) {
        Channel channel = guild.getChannel(message.channelId());
        System.out.println(channel.name());
    }
}
