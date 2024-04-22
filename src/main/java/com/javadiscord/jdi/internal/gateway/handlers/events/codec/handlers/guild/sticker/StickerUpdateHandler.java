package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.sticker;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.cache.CacheInterface;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.Sticker;
import com.javadiscord.jdi.internal.models.message.StickerUpdate;

public class StickerUpdateHandler implements EventHandler<StickerUpdate> {
    @Override
    public void handle(
        StickerUpdate event, ConnectionMediator connectionMediator, Discord discord
    ) {
        CacheInterface<Object> cacheInterface = discord.getCache().getCacheForGuild(event.guildId());
        for (Sticker sticker : event.stickers()) {
            if (cacheInterface.isCached(sticker.id(), Sticker.class)) {
                cacheInterface.update(sticker.id(), sticker);
            }
        }
    }
}
