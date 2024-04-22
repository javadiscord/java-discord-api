package com.javadiscord.jdi.api.application;

import com.javadiscord.jdi.PreTestPrep;
import com.javadiscord.jdi.api.RestAPITest;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.impl.application.EditCurrentApplicationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EditCurrentApplicationRequestTest extends RestAPITest {

    @Test
    void test() throws InterruptedException {
        DiscordRequest request = new EditCurrentApplicationRequest(
                "",
                "",
                "",
                new Object(),
                Map.of(),
                0,
                "",
                "",
                "",
                new String[0]
        );

        DiscordResponseFuture response = PreTestPrep.requestDispatcher.queue(request);
        CountDownLatch latch = new CountDownLatch(1);

        response.onSuccess(
                (r) -> {
                    System.out.printf("Expected status code %s: Got %s%n", 200, r.status());
                    assertEquals(r.status(), 200);
                    latch.countDown();
                }
        );

        response.onError(
                (err) -> {
                    fail();
                }
        );
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
}