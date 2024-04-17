package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.message;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.MessageBulkDelete;

public class MessageBulkDeleteHandler implements EventHandler<MessageBulkDelete> {
    @Override
    public void handle(MessageBulkDelete event, ConnectionMediator connectionMediator,
            Discord discord) {
    }
}
