package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.GetAnswerVotersBuilder;
import com.javadiscord.jdi.internal.api.poll.EndPollRequest;
import com.javadiscord.jdi.internal.api.poll.GetAnswerVotersRequest;

public class Poll {

    public EndPollRequest endPoll(long channelId, long messageId) {
        return new EndPollRequest(channelId, messageId);
    }

    public GetAnswerVotersRequest getAnswerVoters(GetAnswerVotersBuilder builder) {
        return builder.build();
    }
}
