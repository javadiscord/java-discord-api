package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.internal.api.channel.CreateMessageRequest;

import java.util.List;
import java.util.Optional;

@EventListener
public class EchoHandler {

    @MessageCreate
    public void echoMessages(Message message, Discord discord) {
        discord.sendRequest(
                new CreateMessageRequest(
                        message.channelId(),
                        Optional.of(message.content()),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty()));
    }
}
