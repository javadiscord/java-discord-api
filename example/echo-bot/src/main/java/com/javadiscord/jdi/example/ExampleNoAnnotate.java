package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.message.Message;

public class ExampleNoAnnotate implements EventListener {

    @Override
    public void onMessageCreate(Message message, Guild guild) {
        System.out.println("Received a message in ExampleNoAnnotate");
    }
}
