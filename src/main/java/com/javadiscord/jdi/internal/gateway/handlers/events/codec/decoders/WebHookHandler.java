package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.Webhook;

public class WebHookHandler implements EventHandler<Webhook> {
    @Override
    public void handle(Webhook event, ConnectionMediator connectionMediator, Discord discord) {
    }
}
