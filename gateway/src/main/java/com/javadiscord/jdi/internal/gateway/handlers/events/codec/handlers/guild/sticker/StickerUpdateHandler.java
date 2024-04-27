package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.sticker;

import com.javadiscord.jdi.core.models.message.Sticker;
import com.javadiscord.jdi.core.models.message.StickerUpdate;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;

public class StickerUpdateHandler implements EventHandler<StickerUpdate> {
    @Override
    public void handle(StickerUpdate event, ConnectionMediator connectionMediator, Cache cache) {
        CacheInterface<Object> cacheInterface = cache.getCacheForGuild(event.guildId());
        for (Sticker sticker : event.stickers()) {
            if (cacheInterface.isCached(sticker.id(), Sticker.class)) {
                cacheInterface.update(sticker.id(), sticker);
            }
        }
    }
}
