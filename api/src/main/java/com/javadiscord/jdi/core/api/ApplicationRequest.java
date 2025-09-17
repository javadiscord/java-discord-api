package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.EditCurrentApplicationBuilder;
import com.javadiscord.jdi.core.models.application.Application;
import com.javadiscord.jdi.internal.api.application.GetCurrentApplicationRequest;

public class ApplicationRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public ApplicationRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Application> editCurrentApplication(
        EditCurrentApplicationBuilder builder
    ) {
        return responseParser.callAndParse(Application.class, builder.build());
    }

    public AsyncResponse<Application> getCurrentApplication() {
        return responseParser.callAndParse(Application.class, new GetCurrentApplicationRequest());
    }
}
