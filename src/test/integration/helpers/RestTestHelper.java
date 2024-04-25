package helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;

public class RestTestHelper {
    public void sendRequestAndAssert(DiscordRequest request, int expectedStatusCode) throws InterruptedException {
        DiscordResponseFuture response = getDiscordRequestDispatcher().queue(request);
        CountDownLatch latch = new CountDownLatch(1);
        response.onSuccess(
            (r) -> {
                assertEquals(r.status(), expectedStatusCode);
                latch.countDown();
            }
        );
        response.onError(err -> fail());
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    public Discord getDiscord() {
        return MockServerInitializer.getDiscord();
    }

    public DiscordRequestDispatcher getDiscordRequestDispatcher() {
        return getDiscord().getDiscordRequestDispatcher();
    }
}
