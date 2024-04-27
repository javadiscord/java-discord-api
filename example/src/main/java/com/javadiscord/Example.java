package com.javadiscord;

import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.internal.models.message.Message;

@EventListener
public class Example {

    @MessageCreate
    public void onMessageCreate(Message message) {
        System.out.println("Received message: " + message.content());
    }
}
