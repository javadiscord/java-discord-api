package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.GetAnswerVotersBuilder;
import com.javadiscord.jdi.core.models.poll.Poll;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.poll.EndPollRequest;

import java.util.List;

public class PollRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public PollRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Poll> endPoll(long channelId, long messageId) {
        return responseParser.callAndParse(Poll.class, new EndPollRequest(channelId, messageId));
    }

    public AsyncResponse<List<User>> getAnswerVoters(GetAnswerVotersBuilder builder) {
        return responseParser.callAndParseList(User.class, builder.build());
    }
}
