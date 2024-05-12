package com.javadiscord.jdi.core.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.GetInviteBuilder;
import com.javadiscord.jdi.core.models.invite.Invite;

import helpers.LiveDiscordHelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Disabled
class InviteRequestTest {
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    void testGetInvite() throws InterruptedException {
        String testInviteCode = "P4zBwyPuwC";
        CountDownLatch latch = new CountDownLatch(1);
        AsyncResponse<Invite> asyncResponse =
                guild.invite().getInvite(new GetInviteBuilder(testInviteCode));
        asyncResponse.onSuccess(
                invite -> {
                    assertEquals(testInviteCode, invite.code());
                    latch.countDown();
                });
        asyncResponse.onError(Assertions::fail);
        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    @Disabled
    void testDeleteInvite() throws InterruptedException {
        String testInviteCode = "P4zBwyPuwC";
        CountDownLatch latch = new CountDownLatch(1);
        AsyncResponse<Invite> asyncResponse = guild.invite().deleteInvite(testInviteCode);
        asyncResponse.onSuccess(
                invite -> {
                    assertEquals(testInviteCode, invite.code());
                    latch.countDown();
                });
        asyncResponse.onError(Assertions::fail);
        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }
}
