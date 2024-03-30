package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.AutoModerationAction;

public class AutoModerationRuleDeleteHandler implements EventHandler<AutoModerationAction> {
    @Override
    public void handle(
            AutoModerationAction event, ConnectionMediator connectionMediator, Discord discord) {}
}
