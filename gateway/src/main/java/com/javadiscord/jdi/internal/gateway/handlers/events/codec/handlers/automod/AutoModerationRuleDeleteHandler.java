package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod;

import com.javadiscord.jdi.cache.Cache;
import com.javadiscord.jdi.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.auto_moderation.AutoModerationRule;

public class AutoModerationRuleDeleteHandler implements EventHandler<AutoModerationRule> {
    @Override
    public void handle(
        AutoModerationRule event,
        ConnectionMediator connectionMediator,
        Cache cache
    ) {
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(event.guildId());
        if (cacheInterface.isCached(event.id(), event.getClass())) {
            cacheInterface.remove(event.id(), event.getClass());
        }
    }
}
