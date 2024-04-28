package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.voice.ListVoiceRegionsRequest;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class VoiceRequest {
    private final DiscordResponseParser responseParser;

    public VoiceRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public ListVoiceRegionsRequest listVoiceRegions() {
        return new ListVoiceRegionsRequest();
    }
}
