package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.stage.Stage;
import com.javadiscord.jdi.internal.api.stage.DeleteStageRequest;
import com.javadiscord.jdi.internal.api.stage.GetStageRequest;
import com.javadiscord.jdi.internal.request.builders.CreateStageBuilder;
import com.javadiscord.jdi.internal.request.builders.ModifyStageBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class StageRequest {
    private final DiscordResponseParser responseParser;

    public StageRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Stage> createStage(CreateStageBuilder builder) {
        return responseParser.callAndParse(Stage.class, builder.build());
    }

    public AsyncResponse<Stage> deleteStage(long channelId) {
        return responseParser.callAndParse(Stage.class, new DeleteStageRequest(channelId));
    }

    public AsyncResponse<Stage> getStage(long channelId) {
        return responseParser.callAndParse(Stage.class, new GetStageRequest(channelId));
    }

    public AsyncResponse<Stage> modifyStage(ModifyStageBuilder builder) {
        return responseParser.callAndParse(Stage.class, builder.build());
    }
}
