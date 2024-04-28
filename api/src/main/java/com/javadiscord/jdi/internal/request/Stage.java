package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.stage.CreateStageRequest;
import com.javadiscord.jdi.internal.api.stage.DeleteStageRequest;
import com.javadiscord.jdi.internal.api.stage.GetStageRequest;
import com.javadiscord.jdi.internal.api.stage.ModifyStageRequest;
import com.javadiscord.jdi.internal.request.builders.CreateStageBuilder;
import com.javadiscord.jdi.internal.request.builders.ModifyStageBuilder;

public class Stage {

    public CreateStageRequest createStage(CreateStageBuilder builder) {
        return builder.build();
    }

    public DeleteStageRequest deleteStage(long channelId) {
        return new DeleteStageRequest(channelId);
    }

    public GetStageRequest getStage(long channelId) {
        return new GetStageRequest(channelId);
    }

    public ModifyStageRequest modifyStage(ModifyStageBuilder builder) {
        return builder.build();
    }
}
