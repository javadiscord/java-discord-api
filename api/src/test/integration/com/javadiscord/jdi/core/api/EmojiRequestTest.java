package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.utils.DiscordImageUtil;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import helpers.LiveDiscordHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class EmojiRequestTest {
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    void testCreateEmoji() throws InterruptedException, URISyntaxException, IOException {
        CountDownLatch latch = new CountDownLatch(1);

        URL url = EmojiRequestTest.class.getResource("/blep.png");

        if (url == null) {
            fail("/blep.png not found");
            return;
        }

        Path path = Paths.get(url.toURI());

        AsyncResponse<Emoji> asyncResponse = guild.emoji().createEmoji(
                "test",
                DiscordImageUtil.toDiscordString(path),
                List.of());

        asyncResponse.onSuccess(res -> {
            assertEquals("test", res.name());
            assertTrue(res.available());
            assertFalse(res.animated());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }
    @Test
    void testDeleteEmoji() throws InterruptedException, URISyntaxException, IOException {
        AtomicReference<Long> emojiId = new AtomicReference<>();

        {
            CountDownLatch latch = new CountDownLatch(1);

            URL url = EmojiRequestTest.class.getResource("/blep.png");

            if (url == null) {
                fail("/blep.png not found");
                return;
            }

            Path path = Paths.get(url.toURI());

            AsyncResponse<Emoji> asyncResponse = guild.emoji().createEmoji(
                    "toDelete",
                    DiscordImageUtil.toDiscordString(path),
                    List.of());

            asyncResponse.onSuccess(res -> {
                assertEquals("toDelete", res.name());
                assertTrue(res.available());
                assertFalse(res.animated());
                emojiId.set(res.id());
                latch.countDown();
            });

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }

        {
            CountDownLatch latch = new CountDownLatch(1);

            AsyncResponse<Void> asyncResponse = guild.emoji().deleteEmoji(emojiId.get());
            asyncResponse.onSuccess(res ->  latch.countDown());
            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }
    }


}
