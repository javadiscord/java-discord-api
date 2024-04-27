package com.javadiscord.jdi.internal.api.voice;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ListVoiceRegionsRequest() implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().path("/voice/regions").get();
    }
}
