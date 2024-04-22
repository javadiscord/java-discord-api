package com.jdi.internal.api.application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import helpers.RestTestHelper;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.impl.application.GetCurrentApplicationRequest;

import org.junit.jupiter.api.Test;

class GetCurrentApplicationRequestTest extends RestTestHelper {

    @Test
    void test() throws InterruptedException {
        DiscordResponseFuture response = getDiscordRequestDispatcher().queue(new GetCurrentApplicationRequest());
        CountDownLatch latch = new CountDownLatch(1);

        response.onSuccess(
            (r) -> {
                assertEquals(r.status(), 200);
                latch.countDown();
            }
        );

        response.onError(err -> fail());
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
}
