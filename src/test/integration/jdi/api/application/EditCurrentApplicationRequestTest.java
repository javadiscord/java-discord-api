package jdi.api.application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jdi.MockServerInitializer;
import jdi.api.RestTestHelper;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.impl.application.EditCurrentApplicationRequest;

import org.junit.jupiter.api.Test;

class EditCurrentApplicationRequestTest extends RestTestHelper {

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

        DiscordResponseFuture response = getDiscordRequestDispatcher().queue(request);

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
