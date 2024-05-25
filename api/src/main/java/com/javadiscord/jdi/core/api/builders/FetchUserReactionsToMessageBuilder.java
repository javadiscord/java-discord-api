package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.channel.FetchUserReactionsToMessageRequest;

public class FetchUserReactionsToMessageBuilder {
    private final long channelId;
    private final long messageId;
    private final String emoji;
    private Optional<Long> after;
    private Optional<Integer> limit;

    public FetchUserReactionsToMessageBuilder(long channelId, long messageId, String emoji) {
        this.channelId = channelId;
        this.messageId = messageId;
        this.emoji = emoji;
        this.after = Optional.empty();
        this.limit = Optional.empty();
    }

    public FetchUserReactionsToMessageBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public FetchUserReactionsToMessageBuilder limit(int limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public FetchUserReactionsToMessageRequest build() {
        return new FetchUserReactionsToMessageRequest(channelId, messageId, emoji, after, limit);
    }
}
