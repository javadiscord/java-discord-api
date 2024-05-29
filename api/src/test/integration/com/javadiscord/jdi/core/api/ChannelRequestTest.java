package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.api.builders.FetchChannelMessagesBuilder;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.core.models.invite.Invite;
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
import java.util.concurrent.atomic.AtomicReference;

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

    @Test
    void testCreateInvite() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        AsyncResponse<Invite> asyncResponse = guild.channel().createInvite(testChannelId, 10000, 10, true);

        asyncResponse.onSuccess(res -> {
            assertEquals(10000, res.maxAge());
            assertEquals(10, res.maxUses());
            assertTrue(res.temporary());
            assertNotNull(res.code());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testChannelInvites() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        AsyncResponse<List<Invite>> asyncResponse = guild.channel().channelInvites(testChannelId);

        asyncResponse.onSuccess(res -> {
            assertFalse(res.isEmpty());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testTypingIndicator() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        AsyncResponse<Void> asyncResponse = guild.channel().typingIndicatorRequest(testChannelId);

        asyncResponse.onSuccess(res -> latch.countDown());

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testPinAndUnpinMessage() throws InterruptedException {
        AtomicReference<Long> messageId = new AtomicReference<>();

        {
            CountDownLatch latch = new CountDownLatch(1);

            long testChannelId = 1242792813700055134L;

            AsyncResponse<Message> asyncResponse = guild
                    .channel()
                    .createMessage(new CreateMessageBuilder(testChannelId)
                            .content("Hello, World!"));

            asyncResponse.onSuccess(res -> {
                messageId.set(res.id());
                latch.countDown();
            });

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }

        {
            CountDownLatch latch = new CountDownLatch(1);

            long testChannelId = 1242792813700055134L;

            AsyncResponse<Void> asyncResponse = guild
                    .channel()
                    .pinMessage(testChannelId, messageId.get());

            asyncResponse.onSuccess(res -> latch.countDown());

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }

        {
            CountDownLatch latch = new CountDownLatch(1);

            long testChannelId = 1242792813700055134L;

            AsyncResponse<Void> asyncResponse = guild
                    .channel()
                    .unpinMessage(testChannelId, messageId.get());

            asyncResponse.onSuccess(res -> latch.countDown());

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }
    }

    @Test
    void testFetchChannelMessages() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        AsyncResponse<List<Message>> asyncResponse = guild
                .channel()
                .fetchChannelMessages(new FetchChannelMessagesBuilder(testChannelId, 100));

        asyncResponse.onSuccess(res -> {
            assertFalse(res.isEmpty());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testFetchChannel() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        long testChannelId = 1242792813700055134L;

        AsyncResponse<Channel> asyncResponse = guild
                .channel()
                .fetchChannel(testChannelId);

        asyncResponse.onSuccess(res -> {
            assertEquals("general", res.name());
            assertEquals(testChannelId, res.id());
            latch.countDown();
        });

        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }



    @Test
    void testCreateReaction() throws InterruptedException {
        AtomicReference<Long> messageId = new AtomicReference<>();
        long testChannelId = 1242792813700055134L;

        {
            CountDownLatch latch = new CountDownLatch(1);

            AsyncResponse<Message> asyncResponse = guild
                    .channel()
                    .createMessage(new CreateMessageBuilder(testChannelId)
                            .content("Hello, World!"));

            asyncResponse.onSuccess(res -> {
                messageId.set(res.id());
                latch.countDown();
            });

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }

        AtomicReference<Emoji> emoji = new AtomicReference<>();
        {
            CountDownLatch latch = new CountDownLatch(1);
            AsyncResponse<List<Emoji>> asyncResponse = guild
                    .emoji()
                    .getEmojis();

            asyncResponse.onSuccess(res -> {
                assertFalse(res.isEmpty());
                emoji.set(res.getFirst());
                latch.countDown();
            });

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }

        {
            CountDownLatch latch = new CountDownLatch(1);
            AsyncResponse<Void> asyncResponse = guild
                    .channel().createReaction(testChannelId, messageId.get(), emoji.get());

            asyncResponse.onSuccess(res -> latch.countDown());

            asyncResponse.onError(Assertions::fail);

            assertTrue(latch.await(30, TimeUnit.SECONDS));
        }
    }
}
