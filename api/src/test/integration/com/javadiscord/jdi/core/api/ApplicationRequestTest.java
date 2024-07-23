package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.builders.EditCurrentApplicationBuilder;
import com.javadiscord.jdi.core.models.application.Application;
import helpers.LiveDiscordHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class ApplicationRequestTest {

    private static Guild guild;

    @BeforeAll
    public static void setup() throws InterruptedException {
        guild = new LiveDiscordHelper().getGuild();
    }

    @Test
    void testGetApplication() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        AsyncResponse<Application> asyncResponse = guild.application().getCurrentApplication();
        asyncResponse.onSuccess(res -> latch.countDown());
        asyncResponse.onError(Assertions::fail);

        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }

    @Test
    void testEditCurrentApplication() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        AsyncResponse<Application> asyncResponse = guild.application().
                editCurrentApplication(new EditCurrentApplicationBuilder());
        asyncResponse.onSuccess(res -> latch.countDown());
        asyncResponse.onError(Assertions::fail);
        assertTrue(latch.await(30, TimeUnit.SECONDS));
    }
}
