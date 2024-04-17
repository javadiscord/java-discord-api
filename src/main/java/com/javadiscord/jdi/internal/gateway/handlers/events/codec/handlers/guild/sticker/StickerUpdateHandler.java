package com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.guild.sticker;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventHandler;
import com.javadiscord.jdi.internal.models.message.StickerUpdate;

public class StickerUpdateHandler implements EventHandler<StickerUpdate> {
    @Override
    public void handle(
            StickerUpdate event, ConnectionMediator connectionMediator, Discord discord) {}
}
