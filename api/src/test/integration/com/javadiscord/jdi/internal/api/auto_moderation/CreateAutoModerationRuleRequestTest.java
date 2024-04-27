package com.javadiscord.jdi.internal.api.auto_moderation;

import helpers.RestTestHelper;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class CreateAutoModerationRuleRequestTest extends RestTestHelper {

    @Test
    void test() throws InterruptedException {
        sendRequestAndAssert(
                new CreateAutoModerationRuleRequest(
                        0L,
                        "",
                        0,
                        0,
                        Optional.empty(),
                        List.of(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()),
                200);
    }
}
