package com.javadiscord.jdi.api.application;

import com.javadiscord.jdi.PreTestPrep;
import com.javadiscord.jdi.api.RestAPITest;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.impl.application.EditCurrentApplicationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EditCurrentApplicationRequestTest extends RestAPITest {

    @Test
    void test() throws InterruptedException {
        testRequest(new EditCurrentApplicationRequest(
                "",
                "",
                "",
                new Object(),
                Map.of(),
                0,
                "",
                "",
                "",
                new String[0]
        ), 200);
    }
}