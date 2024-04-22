package com.javadiscord.jdi.api;

import com.javadiscord.jdi.PreTestPrep;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(PreTestPrep.class)
public class RestAPITest {

    // this can be changed later to
    // return the response or smth when we impl that.
    public void testRequest(DiscordRequest request, int expectedStatusCode) throws InterruptedException {
        DiscordResponseFuture response = PreTestPrep.requestDispatcher.queue(request);
        CountDownLatch latch = new CountDownLatch(1);

        response.onSuccess(
                (r) -> {
                    System.out.printf("Expected status code %s: Got %s%n", expectedStatusCode, r.status());
                    assertEquals(r.status(), expectedStatusCode);
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
