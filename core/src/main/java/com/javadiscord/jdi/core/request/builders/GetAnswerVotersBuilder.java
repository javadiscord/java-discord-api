package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.poll.GetAnswerVotersRequest;

import java.util.Optional;

public class GetAnswerVotersBuilder {
    private final long channelId;
    private final long messageId;
    private final long answerId;
    private Optional<Long> after;
    private Optional<Integer> limit;

    public GetAnswerVotersBuilder(long channelId, long messageId, long answerId) {
        this.channelId = channelId;
        this.messageId = messageId;
        this.answerId = answerId;
        after = Optional.empty();
        limit = Optional.empty();
    }

    public GetAnswerVotersBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetAnswerVotersBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetAnswerVotersRequest build() {
        return new GetAnswerVotersRequest(channelId, messageId, answerId, after, limit);
    }
}
