package com.javadiscord.jdi.api.automoderation;

import com.javadiscord.jdi.PreTestPrep;
import com.javadiscord.jdi.api.RestAPITest;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.impl.automoderation.CreateAutoModerationRuleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CreateAutoModerationRuleRequestTest extends RestAPITest {

    @Test
    void test() throws InterruptedException {
        testRequest(new CreateAutoModerationRuleRequest(
                0L,
                "",
                0,
                0,
                Optional.empty(),
                List.of(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ), 200);
    }
}