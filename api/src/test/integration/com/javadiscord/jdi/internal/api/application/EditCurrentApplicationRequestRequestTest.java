package com.javadiscord.jdi.internal.api.application;

import static org.junit.jupiter.api.Assertions.*;

import com.javadiscord.jdi.core.models.application.ApplicationInstallParams;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;

import helpers.RestTestHelper;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class EditCurrentApplicationRequestRequestTest extends RestTestHelper {

    @Test
    void test() throws InterruptedException {
        DiscordRequest request =
                new EditCurrentApplicationRequest(
                        "",
                        "",
                        "",
                        new ApplicationInstallParams(List.of(), ""),
                        Map.of(),
                        0,
                        "",
                        "",
                        "",
                        List.of());

        DiscordResponseFuture response = getDiscordRequestDispatcher().queue(request);

        CountDownLatch latch = new CountDownLatch(1);

        response.onSuccess(
                (r) -> {
                    assertEquals(r.status(), 200);
                    latch.countDown();
                });

        response.onError(err -> fail());

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
}
