package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.CreateStageBuilder;
import com.javadiscord.jdi.core.api.builders.ModifyStageBuilder;
import com.javadiscord.jdi.core.models.stage.Stage;
import com.javadiscord.jdi.internal.api.stage.DeleteStageRequest;
import com.javadiscord.jdi.internal.api.stage.GetStageRequest;

public class StageRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public StageRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
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
