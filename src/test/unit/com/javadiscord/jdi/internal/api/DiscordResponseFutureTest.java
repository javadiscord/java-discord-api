package com.javadiscord.jdi.internal.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscordResponseFutureTest {

    @Test
    void testSuccessConsumer() {
        DiscordResponseFuture future = new DiscordResponseFuture();
        future.onSuccess(res -> {
            assertEquals(200, res.status());
            assertEquals("OK", res.body());
        });
        future.onError(res -> fail());

        DiscordResponse mockDiscordResponse = mock(DiscordResponse.class);
        when(mockDiscordResponse.status()).thenReturn(200);
        when(mockDiscordResponse.body()).thenReturn("OK");

        future.setResult(mockDiscordResponse);
    }

    @Test
    void testErrorConsumer() {
        String mockExceptionMessage = "dummy-exception";

        RuntimeException mockException = mock(RuntimeException.class);
        Throwable mockThrowable = mock(Throwable.class);

        when(mockThrowable.getMessage()).thenReturn(mockExceptionMessage);
        when(mockException.getCause()).thenReturn(mockThrowable);

        DiscordResponseFuture future = new DiscordResponseFuture()
                .onSuccess(res -> fail())
                .onError(res -> assertEquals(mockExceptionMessage, res.getCause().getMessage()));

        future.setException(mockException);
    }

}
