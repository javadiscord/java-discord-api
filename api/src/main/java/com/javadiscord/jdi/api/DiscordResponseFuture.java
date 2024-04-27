package com.javadiscord.jdi.api;

public class DiscordResponseFuture {
    @FunctionalInterface
    public interface SuccessConsumer<DiscordResponse> {
        void accept(DiscordResponse result);
    }

    @FunctionalInterface
    public interface ExceptionConsumer {
        void accept(Throwable result);
    }

    private SuccessConsumer<DiscordResponse> successConsumer;
    private ExceptionConsumer exceptionConsumer;

    public void setResult(DiscordResponse discordResponse) {
        if (successConsumer != null) {
            successConsumer.accept(discordResponse);
        }
    }

    public void setException(Throwable exception) {
        if (exceptionConsumer != null) {
            exceptionConsumer.accept(exception);
        }
    }

    public DiscordResponseFuture onSuccess(SuccessConsumer<DiscordResponse> successConsumer) {
        this.successConsumer = successConsumer;
        return this;
    }

    public DiscordResponseFuture onError(ExceptionConsumer exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
        return this;
    }
}
