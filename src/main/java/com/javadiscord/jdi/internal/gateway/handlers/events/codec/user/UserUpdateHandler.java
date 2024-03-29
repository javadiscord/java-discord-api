package com.javadiscord.jdi.internal.gateway.handlers.events.codec.user;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.user.User;

public class UserUpdateHandler implements EventHandler<User> {
    @Override
    public void handle(User event, ConnectionMediator connectionMediator, Discord discord) {

    }
}
