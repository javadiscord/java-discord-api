package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;
import helpers.LiveDiscordHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ChannelRequestTest {
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    void testSendFile() throws InterruptedException, URISyntaxException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        URL url = ChannelRequestTest.class.getResource("/cat.jpg");

        if (url == null) {
            fail("/cat.jpg not found");
            return;
        }

        Path path = Paths.get(url.toURI());

        AsyncResponse<Message> asyncResponse = guild
                .channel()
                .createMessage(new CreateMessageBuilder(testChannelId)
                .files(List.of(path)));

        asyncResponse.onSuccess(res -> {
            assertEquals(testChannelId, res.channelId());
            assertEquals("cat.jpg", res.messageAttachments().getFirst().filename());
            assertEquals("image/jpeg", res.messageAttachments().getFirst().contentType());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testSendMessageWithFile() throws InterruptedException, URISyntaxException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        URL url = ChannelRequestTest.class.getResource("/cat.jpg");

        if (url == null) {
            fail("/cat.jpg not found");
            return;
        }

        Path path = Paths.get(url.toURI());

        AsyncResponse<Message> asyncResponse = guild
                .channel()
                .createMessage(new CreateMessageBuilder(testChannelId)
                        .files(List.of(path))
                        .content("Hello, World!"));

        asyncResponse.onSuccess(res -> {
            System.out.println(res);
            assertEquals(testChannelId, res.channelId());
            assertEquals("cat.jpg", res.messageAttachments().getFirst().filename());
            assertEquals("image/jpeg", res.messageAttachments().getFirst().contentType());
            assertEquals("Hello, World!", res.content());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }
}
