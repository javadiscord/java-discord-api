package com.javadiscord.jdi.internal.api;

public final class Response<T> {
    @FunctionalInterface
    public interface SuccessConsumer<T> {
        void accept(T result);
    }

    @FunctionalInterface
    public interface ExceptionConsumer {
        void accept(Throwable result);
    }

    private SuccessConsumer<T> successConsumer;
    private ExceptionConsumer exceptionConsumer;

    public void setResult(T result) {
        if (successConsumer != null) {
            successConsumer.accept(result);
        }
    }

    public void setException(Throwable exception) {
        if (exceptionConsumer != null) {
            exceptionConsumer.accept(exception);
        }
    }

    public Response<T> onSuccess(SuccessConsumer<T> successConsumer) {
        this.successConsumer = successConsumer;
        return this;
    }

    public Response<T> onError(ExceptionConsumer exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
        return this;
    }
}
