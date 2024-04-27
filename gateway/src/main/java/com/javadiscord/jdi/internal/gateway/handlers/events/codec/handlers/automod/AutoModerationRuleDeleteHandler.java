package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.automod;

import com.javadiscord.jdi.core.models.auto_moderation.AutoModerationRule;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class AutoModerationRuleDeleteHandler implements EventHandler<AutoModerationRule> {
    @Override
    public void handle(
            AutoModerationRule event, ConnectionMediator connectionMediator, Cache cache) {
        CacheInterface<?> cacheInterface = cache.getCacheForGuild(event.guildId());
        if (cacheInterface.isCached(event.id(), event.getClass())) {
            cacheInterface.remove(event.id(), event.getClass());
        }
    }
}
