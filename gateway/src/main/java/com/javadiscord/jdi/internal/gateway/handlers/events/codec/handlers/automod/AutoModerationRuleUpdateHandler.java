package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationRule;

public class AutoModerationRuleUpdateHandler implements EventHandler<AutoModerationRule> {
    @Override
    public void handle(
            AutoModerationRule event, ConnectionMediator connectionMediator, Cache cache) {
        cache.getCacheForGuild(event.guildId()).update(event.id(), event);
    }
}
