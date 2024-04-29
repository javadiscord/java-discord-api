package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class GuildRequest {
    private final DiscordResponseParser responseParser;

    public GuildRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }
}
