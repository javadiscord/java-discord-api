package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.internal.request.builders.GetAuditLogsBuilder;

public class ExampleNoAnnotate implements EventListener {

    @Override
    public void onMessageCreate(Message message, Guild guild) {
        System.out.println("Received a message in ExampleNoAnnotate");
        guild.channel()
                .createInvite(1232064259085828228L, 1000, 10, true)
                .onSuccess(res -> System.out.println("Result: " + res))
                .onError(System.err::println);

        guild.auditLogs()
                .getAuditLogs(new GetAuditLogsBuilder())
                .onSuccess(res -> System.out.println("Result: " + res))
                .onError(System.err::println);
    }
}
