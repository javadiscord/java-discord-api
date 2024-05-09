package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.models.voice.VoiceRegion;
import com.javadiscord.jdi.internal.api.voice.ListVoiceRegionsRequest;

import java.util.List;

public class VoiceRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public VoiceRequest(DiscordResponseParser responseParser, long guildId) {
        this.guildId = guildId;
        this.responseParser = responseParser;
    }

    public AsyncResponse<List<VoiceRegion>> listVoiceRegions() {
        return responseParser.callAndParseList(VoiceRegion.class, new ListVoiceRegionsRequest());
    }
}
