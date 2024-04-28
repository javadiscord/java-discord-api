package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.message.Message;

@EventListener
public class EchoHandler {

    @MessageCreate
    public void echoMessages(Message message, Guild guild) {
        System.out.println("EchoHandler");
    }
}
