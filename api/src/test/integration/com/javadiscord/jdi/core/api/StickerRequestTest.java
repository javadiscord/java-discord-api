package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.ModifyGuildStickerBuilder;
import com.javadiscord.jdi.core.models.message.Sticker;
import com.javadiscord.jdi.core.models.message.StickerFormatType;
import com.javadiscord.jdi.core.models.message.StickerType;
import helpers.LiveDiscordHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class StickerRequestTest {
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    void testCreateGuildStickerThenGetAndDelete() throws InterruptedException, URISyntaxException {
        CountDownLatch latch = new CountDownLatch(1);

        String stickerName = "sticker-" + ThreadLocalRandom.current().nextInt(1,10);
        String description = "d-" + ThreadLocalRandom.current().nextInt(1,10);
        String tags = "tag-" + ThreadLocalRandom.current().nextInt(1,10);

        URL url = StickerRequestTest.class.getResource("/test-sticker.png");

        if (url == null) {
            fail("/test-sticker.png not found");
            return;
        }

        AsyncResponse<Sticker> asyncResponse = guild
                .sticker()
                .createGuildSticker(stickerName, description, tags, Paths.get(url.toURI()));

        AtomicReference<Long> stickerId = new AtomicReference<>();

        asyncResponse.onSuccess(res -> {
            assertEquals(stickerName, res.name());
            assertEquals(description, res.description());
            assertEquals(tags, res.tags());
            assertEquals(StickerType.GUILD, res.type());
            assertEquals(StickerFormatType.PNG, res.formatType());
            stickerId.set(res.id());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));

        if(stickerId.get() != null) {
            guild.sticker().deleteGuildSticker(stickerId.get())
                    .onError(Assertions::fail);
        } else {
            fail();
        }
    }

    @Test
    void testModifySticker() throws InterruptedException, URISyntaxException {
        CountDownLatch createLatch = new CountDownLatch(1);

        URL testSticker = StickerRequestTest.class.getResource("/test-sticker.png");
        URL testSticker2 = StickerRequestTest.class.getResource("/test-sticker-2.png");

        if (testSticker == null) {
            fail("/test-sticker.png not found");
            return;
        }
        if (testSticker2 == null) {
            fail("/test-sticker-2.png not found");
            return;
        }

        AtomicReference<Long> stickerId = new AtomicReference<>();

        guild.sticker()
                .createGuildSticker("test-sticker", "description", "tags", Paths.get(testSticker.toURI()))
                .onSuccess(res -> {
                    stickerId.set(res.id());
                    createLatch.countDown();
                })
                .onError(Assertions::fail);

        assertTrue(createLatch.await(30, TimeUnit.SECONDS));

        CountDownLatch modifyLatch = new CountDownLatch(1);

        guild.sticker()
                .modifyGuildSticker(new ModifyGuildStickerBuilder(stickerId.get())
                        .description("new description")
                        .name("new name")
                        .tags("new tags"))
                .onSuccess(res -> {
                    assertEquals("new name", res.name());
                    assertEquals("new description", res.description());
                    assertEquals("new tags", res.tags());
                    modifyLatch.countDown();
                })
                .onError(err -> {
                    System.err.println("error: " + err.getMessage());
                });

        assertTrue(modifyLatch.await(30, TimeUnit.SECONDS));

        CountDownLatch deleteLatch = new CountDownLatch(1);

        AsyncResponse<Void> delete = guild.sticker()
                .deleteGuildSticker(stickerId.get());

        delete.onSuccess(res -> deleteLatch.countDown());
        delete.onError(Assertions::fail);

        assertTrue(deleteLatch.await(30, TimeUnit.SECONDS));
    }
}
