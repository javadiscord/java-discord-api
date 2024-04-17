package com.javadiscord.jdi.internal.api.impl.user;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public final record LeaveGuildRequest(long guildId) implements DiscordRequest {
	@Override
	public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/users/@me/guilds/" + guildId);
	}
}
