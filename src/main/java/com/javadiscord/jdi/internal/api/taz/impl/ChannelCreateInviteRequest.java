package com.javadiscord.jdi.internal.api.taz.impl;

import com.javadiscord.jdi.internal.api.taz.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.api.taz.DiscordRequestInterface;

import java.util.Map;

public class ChannelCreateInviteRequest implements DiscordRequestInterface {
    private final int channelId;
    private final int maxAge;
    private final int maxUses;
    private final boolean temporary;

    public ChannelCreateInviteRequest(int channelId, int maxAge, int maxUses, boolean temporary) {
        this.channelId = channelId;
        this.maxAge = maxAge;
        this.maxUses = maxUses;
        this.temporary = temporary;
    }

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .url("/channels/%s/invites".formatted(channelId))
                .body(
                        Map.of(
                                "max_age", maxAge,
                                "max_uses", maxUses,
                                "temporary", temporary));
    }
}
