package com.javadiscord.jdi.core.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.voice.VoiceRegion;

import helpers.LiveDiscordHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class VoiceRequestTest {
    private static final Logger LOGGER = LogManager.getLogger(VoiceRequestTest.class);
    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    /*

    @Test
    void testListVoiceRegions() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AsyncResponse<List<VoiceRegion>> asyncResponse = guild.voice().listVoiceRegions();
        asyncResponse.onSuccess(
                regions -> {
                    assertFalse(regions.isEmpty());
                    latch.countDown();
                });
        asyncResponse.onError(
                err -> {
                    LOGGER.error("testListVoiceRegions test failed", err);
                    Assertions.fail(err);
                });
        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

     */
}
