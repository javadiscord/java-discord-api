package com.javadiscord.gateway.handlers.events.codec.channel;

import com.javadiscord.Discord;
import com.javadiscord.discord.channel.Channel;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.handlers.events.codec.EventHandler;

public class ChannelCreateHandler implements EventHandler<Channel> {

    @Override
    public void handle(Channel event, ConnectionMediator connectionMediator, Discord discord) {
        System.out.println("Channel created " + event);
    }
}
