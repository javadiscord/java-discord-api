package com.javadiscord.jdi.core.api;

import static org.junit.jupiter.api.Assertions.*;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.ModifyCurrentUserBuilder;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.channel.ChannelType;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;

import helpers.LiveDiscordHelper;

import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class UserRequestTest {
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    @Disabled
    void testGetCurrentUserGuildMember() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncResponse<Member> asyncResponse = guild.user().getCurrentUserGuildMember();
        asyncResponse.onSuccess(res -> fail());
        asyncResponse.onError(
                err -> {
                    assertTrue(err.getMessage().contains("Bots cannot use this endpoint"));
                    countDownLatch.countDown();
                });
        assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testGetCurrentUser() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncResponse<User> asyncResponse = guild.user().getCurrentUser();
        asyncResponse.onSuccess(
                user -> {
                    assertEquals(1242792739129790475L, user.id());
                    assertTrue(user.bot());
                    countDownLatch.countDown();
                });
        asyncResponse.onError(Assertions::fail);
        assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
    }

    /* DISABLED Until tests are reworked
    @Test
    void testGetUser() throws InterruptedException {
        long wazeiUserId = 821143476455342120L;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncResponse<User> asyncResponse = guild.user().getUser(wazeiUserId);
        asyncResponse.onSuccess(
                user -> {
                    assertEquals(wazeiUserId, user.id());
                    assertEquals("wazei", user.displayName());
                    assertEquals("0", user.discriminator());
                    assertFalse(user.bot());
                    countDownLatch.countDown();
                });
        asyncResponse.onError(Assertions::fail);
        assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
    }

     */

    @Test
    @Disabled
    void testModifyCurrentUser() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            AsyncResponse<User> asyncResponse =
                    guild.user().modifyCurrentUser(new ModifyCurrentUserBuilder("dummy"));
            asyncResponse.onSuccess(
                    user -> {
                        assertEquals("dummy", user.username());
                        assertTrue(user.bot());
                        countDownLatch.countDown();
                    });
            asyncResponse.onError(Assertions::fail);
            assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
        } finally {
            guild.user().modifyCurrentUser(new ModifyCurrentUserBuilder("JDA"));
        }
    }

    @Test
    @Disabled
    void leaveGuild() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncResponse<Void> asyncResponse = guild.user().leaveGuild();
        asyncResponse.onSuccess(v -> countDownLatch.countDown());
        asyncResponse.onError(Assertions::fail);
        assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testCreateDM() throws InterruptedException {
        long wazeiUserId = 821143476455342120L;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncResponse<Channel> asyncResponse = guild.user().createDM(wazeiUserId);
        asyncResponse.onSuccess(
                channel -> {
                    assertEquals(ChannelType.DM, channel.type());
                    countDownLatch.countDown();
                });
        asyncResponse.onError(Assertions::fail);
        assertTrue(countDownLatch.await(30, TimeUnit.SECONDS));
    }
}
