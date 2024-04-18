package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.guild.AutoModerationRuleObject;

public class AutoModerationRuleUpdateHandler implements EventHandler<AutoModerationRuleObject> {
    @Override
    public void handle(
            AutoModerationRuleObject event,
            ConnectionMediator connectionMediator,
            Discord discord) {}
}
