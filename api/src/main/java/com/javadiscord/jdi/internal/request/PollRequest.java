package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.poll.Poll;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.poll.EndPollRequest;
import com.javadiscord.jdi.internal.request.builders.GetAnswerVotersBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class PollRequest {
    private final DiscordResponseParser responseParser;

    public PollRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Poll> endPoll(long channelId, long messageId) {
        return responseParser.callAndParse(Poll.class, new EndPollRequest(channelId, messageId));
    }

    public AsyncResponse<List<User>> getAnswerVoters(GetAnswerVotersBuilder builder) {
        return responseParser.callAndParseList(User.class, builder.build());
    }
}
