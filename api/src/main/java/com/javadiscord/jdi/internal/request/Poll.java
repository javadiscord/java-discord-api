package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.poll.EndPollRequest;
import com.javadiscord.jdi.internal.api.poll.GetAnswerVotersRequest;
import com.javadiscord.jdi.internal.request.builders.GetAnswerVotersBuilder;

public class Poll {

    public EndPollRequest endPoll(long channelId, long messageId) {
        return new EndPollRequest(channelId, messageId);
    }

    public GetAnswerVotersRequest getAnswerVoters(GetAnswerVotersBuilder builder) {
        return builder.build();
    }
}
