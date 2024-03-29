package com.javadiscord.example;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.annotations.ChannelCreate;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.internal.models.channel.Channel;

@EventListener
public class ExampleListener {

    @ChannelCreate
    public void exampleChannelCreate(Channel channel) {
        System.out.printf("Channel created: %s", channel.name());
    }

    @ChannelCreate
    public void exampleChannelCreate(Channel channel, Discord discord) {
        if (channel.name().equals("gaming")) {
            // discord.getGuild(channel.guildId())
            //        .getChannel(channel.id())
            //        .sendMessage("Hello, World!");
        }
    }

    /*@MessageCreate
    public void exampleMessageCreate(Message message) {
        Database.save(message);
    }*/
}
